package com.github.synaos.vulnerabilitiescache.maven;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonEmpty;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.lang.System.getProperty;
import static java.lang.reflect.Modifier.isStatic;
import static java.time.Duration.between;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.util.Collections.synchronizedMap;
import static java.util.Optional.ofNullable;
import static org.apache.maven.settings.TrackableBase.GLOBAL_LEVEL;
import static org.codehaus.plexus.util.StringUtils.isEmpty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.naming.event.EventContext;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.DefaultArtifactRepository;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.settings.RuntimeInfo;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.SettingsUtils;
import org.apache.maven.settings.io.xpp3.SettingsXpp3Reader;
import org.apache.maven.wagon.events.TransferEvent;
import org.apache.maven.wagon.events.TransferListener;
import org.apache.maven.wagon.resource.Resource;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.PlexusContainerException;
import org.codehaus.plexus.component.discovery.ComponentDiscoveryEvent;
import org.codehaus.plexus.component.repository.ComponentDescriptor;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.interpolation.EnvarBasedValueSource;
import org.codehaus.plexus.interpolation.RegexBasedInterpolator;
import org.codehaus.plexus.util.ReaderFactory;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Support {

    private static final Logger LOG = LoggerFactory.getLogger(Support.class);

    @Nonnull
    private static final ArtifactRepositoryLayout artifactRepositoryLayout = new DefaultRepositoryLayout();

    @Nonnull
    static ArtifactRepository localArtifactRepositoryOf(@Nonnull Settings settings) {
        requireNonNull(settings, "settings");
        final var localRepositoryPath = Paths.get(requireNonEmpty(settings.getLocalRepository(), "settings.localRepository"));
        return new DefaultArtifactRepository("local", localRepositoryPath.toUri().toString(), artifactRepositoryLayout);
    }

    @Nonnull
    static Settings settingsOf(@Nonnull Path... paths) {
        final var userHome = Optional.ofNullable(getProperty("user.home"))
            .orElseThrow(() -> new IllegalStateException("user.home not set"));
        final var settings = new Settings();

        Optional.ofNullable(System.getenv("M2_HOME"))
            .filter(v -> !v.isEmpty())
            .map(v -> Paths.get(v, "conf", "settings.xml"))
            .flatMap(v -> tryReadSettingsFrom(v, true))
            .ifPresent(v -> SettingsUtils.merge(v, settings, GLOBAL_LEVEL));

        Optional.of(Paths.get(userHome, ".m2", "settings.xml"))
            .flatMap(v -> tryReadSettingsFrom(v, false))
            .ifPresent(v -> SettingsUtils.merge(v, settings, GLOBAL_LEVEL));

        for (final var path : paths) {
            ofNullable(path)
                .map(Support::readSettingsFrom)
                .ifPresent(v -> SettingsUtils.merge(v, settings, GLOBAL_LEVEL));
        }

        if (isEmpty(settings.getLocalRepository())) {
            settings.setLocalRepository(Paths.get(userHome, ".m2", "repository").toAbsolutePath().toString());
        }

        return settings;
    }

    @Nonnull
    private static Optional<Settings> tryReadSettingsFrom(@Nonnull Path file, boolean parseErrorAsEmpty) {
        try (final var xmlReader = ReaderFactory.newXmlReader(file.toFile())) {

            final var interpolator = new RegexBasedInterpolator();
            interpolator.addValueSource(new EnvarBasedValueSource());

            final var modelReader = new SettingsXpp3Reader();
            final var settings = modelReader.read(xmlReader);

            return Optional.of(settings);
        } catch (FileNotFoundException | NoSuchFileException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot parse Maven Settings file: " + file, e);
        } catch (XmlPullParserException e) {
            if (parseErrorAsEmpty) {
                return Optional.empty();
            }
            throw new IllegalArgumentException("Cannot parse Maven Settings file: " + file, e);
        }
    }

    @Nonnull
    private static Settings readSettingsFrom(@Nonnull Path file) {
        return tryReadSettingsFrom(file, false)
            .orElseThrow(() -> new IllegalArgumentException("Cannot find settings file: " + file));
    }

    static final class LogTransferListener implements TransferListener {

        private static class Progress {

            @Nonnull
            private final LocalDateTime startTime = now();
            @Nonnull
            private final AtomicLong progress = new AtomicLong();
        }

        @Nonnull
        private final Logger logger;

        @Nonnull
        private final Map<Resource, Progress> knownProgresses = synchronizedMap(new WeakHashMap<>());

        LogTransferListener(@Nonnull Logger logger) {
            this.logger = requireNonNull(logger, "logger");
        }

        @Override
        public void transferInitiated(@Nonnull TransferEvent e) {}

        @Override
        public void transferStarted(@Nonnull TransferEvent e) {
            knownProgresses.put(e.getResource(), new Progress());
        }

        @Override
        public void transferProgress(@Nonnull TransferEvent e, byte[] buffer, int length) {
            var l = logger.atDebug()
                .addKeyValue("resource", e.getResource());

            final var progress = knownProgresses.get(e.getResource());
            if (progress != null) {
                l = l.addKeyValue("bytes", progress.progress.addAndGet(length));
            }

            l.log("Progress...");
        }

        @Override
        public void transferCompleted(@Nonnull TransferEvent e) {
            var l = logger.atInfo()
                .addKeyValue("resource", e.getResource());

            final var progress = knownProgresses.get(e.getResource());
            if (progress != null) {
                l = l
                    .addKeyValue("bytes", progress.progress.get())
                    .addKeyValue("duration", between(progress.startTime, now()).truncatedTo(MILLIS));
            }

            l.log("Transfer finished.");
        }

        @Override
        public void transferError(final TransferEvent transferEvent) {
            logger.error("Transfer failed.", transferEvent.getException());
        }

        @Override
        public void debug(String message) {
            logger.debug(message);
        }
    }

    @Nonnull
    static <T> T lookup(@Nonnull Class<T> type, @Nonnull PlexusContainer in) {
        requireNonNull(type, "type");
        requireNonNull(in, "in");
        try {
            final var roleField = type.getDeclaredField("ROLE");
            if (!isStatic(roleField.getModifiers())) {
                throw new IllegalArgumentException("ROLE field of " + type.getName() + " is not static.");
            }
            if (!roleField.getType().equals(String.class)) {
                throw new IllegalArgumentException("ROLE field of " + type.getName() + " is not a String.");
            }
            final var componentKey = (String) roleField.get(null);
            return type.cast(in.lookup(componentKey));
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException(type.getName() + " does not contain a ROLE field.");
        } catch (IllegalAccessException | ComponentLookupException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    static PlexusContainer newStandalonePlexusContainer() {
        final var container = new DefaultPlexusContainer();
        try {
            container.initialize();
            container.registerComponentDiscoveryListener(Support::removeAllWithConfigurationFrom);
            container.start();
        } catch (PlexusContainerException e) {
            throw new RuntimeException(e);
        }
        container.setLoggerManager(new Slf4jToPlexusLoggerManager());
        return container;
    }

    private static void removeAllWithConfigurationFrom(@Nullable ComponentDiscoveryEvent event) {
        if (event == null) {
            return;
        }

        final var descriptorSet = event.getComponentSetDescriptor();
        if (descriptorSet == null) {
            return;
        }

        //noinspection unchecked
        final var descriptors = (List<ComponentDescriptor>) descriptorSet.getComponents();
        if (descriptors == null || descriptors.isEmpty()) {
            return;
        }

        final var config = descriptors.get(0).getConfiguration();
        if (config.getChildren().length == 0) {
            return;
        }

        descriptorSet.setComponents(List.of());
    }

}
