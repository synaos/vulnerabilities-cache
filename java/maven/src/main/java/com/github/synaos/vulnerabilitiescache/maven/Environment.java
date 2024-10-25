package com.github.synaos.vulnerabilitiescache.maven;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Throwables.throwSneaky;
import static com.github.synaos.vulnerabilitiescache.maven.Support.*;
import static java.lang.System.getenv;
import static java.nio.file.Files.isDirectory;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.manager.WagonManager;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.*;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.profiles.DefaultProfileManager;
import org.apache.maven.project.DefaultProjectBuilderConfiguration;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectBuilder;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.project.artifact.InvalidDependencyVersionException;
import org.apache.maven.settings.Settings;
import org.codehaus.plexus.PlexusContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Environment {

    private static final Logger LOG = LoggerFactory.getLogger(Environment.class);

    @Nonnull
    private static final Environment standaloneInstance = ofNullable(getenv("MAVEN_SETTINGS"))
        .filter(v -> !v.isEmpty())
        .map(Paths::get)
        .map(Environment::of)
        .orElseGet(Environment::of);

    @Nonnull
    public static Environment standaloneEnvironment() {
        return standaloneInstance;
    }

    @Nonnull
    public static Environment of(@Nonnull Path... paths) {
        final var settings = settingsOf(paths);

        final var container = newStandalonePlexusContainer();

        final var wagonManager = lookup(WagonManager.class, container);
        wagonManager.setDownloadMonitor(new LogTransferListener(LOG));
        if (settings.isOffline()) {
            LOG.info("You're working in offline mode.");
            wagonManager.setOnline(false);
        } else {
            wagonManager.setOnline(true);
        }

        return new Environment(
            container,
            settings,
            localArtifactRepositoryOf(settings),
            new Properties(),
            new Properties()
        );
    }

    @Nonnull
    public static Environment of(@Nonnull MavenSession session) {
        requireNonNull(session, "session");

        //noinspection deprecation
        return new Environment(
            session.getContainer(),
            session.getSettings(),
            session.getLocalRepository(),
            session.getUserProperties(),
            session.getSystemProperties()
        );
    }

    @Nonnull
    private final Settings settings;
    @Nonnull
    private final ArtifactRepository localRepository;
    @Nonnull
    private final Properties userProperties;
    @Nonnull
    private final Properties executionProperties;
    @Nonnull
    private final PlexusContainer container;
    @Nonnull
    private final MavenProjectBuilder mavenProjectBuilder;
    @Nonnull
    private final ArtifactMetadataSource artifactMetadataSource;
    @Nonnull
    private final ArtifactResolver artifactResolver;
    @Nonnull
    private final ArtifactFactory artifactFactory;

    private Environment(
        @Nonnull PlexusContainer container,
        @Nonnull Settings settings,
        @Nonnull ArtifactRepository localRepository,
        @Nonnull Properties userProperties,
        @Nonnull Properties executionProperties
    ) {
        this.container = requireNonNull(container, "container");
        this.settings = requireNonNull(settings, "settings");
        this.localRepository = requireNonNull(localRepository, "localRepository");
        this.userProperties = requireNonNull(userProperties, "userProperties");
        this.executionProperties = requireNonNull(executionProperties, "executionProperties");

        this.mavenProjectBuilder = lookup(MavenProjectBuilder.class, container);
        this.artifactMetadataSource = lookup(ArtifactMetadataSource.class, container);
        this.artifactResolver = lookup(ArtifactResolver.class, container);
        this.artifactFactory = lookup(ArtifactFactory.class, container);
    }

    @Nonnull
    PlexusContainer container() {
        return container;
    }

    @Nonnull
    Settings settings() {
        return settings;
    }

    @Nonnull
    ArtifactRepository localRepository() {
        return localRepository;
    }

    @Nonnull
    Properties userProperties() {
        return userProperties;
    }

    @Nonnull
    Properties executionProperties() {
        return executionProperties;
    }

    @Nonnull
    MavenProjectBuilder mavenProjectBuilder() {
        return mavenProjectBuilder;
    }

    @Nonnull
    ArtifactMetadataSource artifactMetadataSource() {
        return artifactMetadataSource;
    }

    @Nonnull
    ArtifactResolver artifactResolver() {
        return artifactResolver;
    }

    @Nonnull
    ArtifactFactory artifactFactory() {
        return artifactFactory;
    }

    @Nonnull
    public MavenProject mavenProjectOf(@Nonnull Path path, boolean withDependencies) {
        final var config = new DefaultProjectBuilderConfiguration()
            .setBuildStartTime(new Date())
            .setLocalRepository(localRepository())
            .setUserProperties(userProperties())
            .setGlobalProfileManager(new DefaultProfileManager(container(), settings(), userProperties()))
            .setExecutionProperties(executionProperties());
        try {
            if (isDirectory(path)) {
                path = path.resolve("pom.xml");
            }
            final var project = mavenProjectBuilder.build(
                path.toFile(),
                config.getLocalRepository(),
                config.getGlobalProfileManager(),
                !withDependencies
            );

            if (withDependencies) {
                resolveArtifactsOf(project);
            }

            return project;
        } catch (ProjectBuildingException | ArtifactResolutionException | ArtifactNotFoundException | InvalidDependencyVersionException e) {
            return throwSneaky(e);
        }
    }

    private void resolveArtifactsOf(@Nonnull MavenProject project) throws InvalidDependencyVersionException, ArtifactResolutionException, ArtifactNotFoundException {
        requireNonNull(project, "project");

        final var reactorProjects = new HashSet<MavenProject>(25);
        collectRecursive(project, reactorProjects);
        final var reactorArtifacts = reactorProjects.stream()
            .map(v -> v.getGroupId() + ":" + v.getArtifactId())
            .collect(toSet());

        // TODO! Yeah this kind of hacky... We should see how we can resolve this better in the future...
        final ArtifactFilter artifactFilter = candidate -> !reactorArtifacts.contains(candidate.getGroupId() + ":" + candidate.getArtifactId());

        final var dependencyArtifacts = project.createArtifacts(artifactFactory(), null, artifactFilter);
        final var projectArtifact = project.getArtifact();
        final var managedVersions = project.getManagedVersionMap();
        final var remoteArtifactRepositories = project.getRemoteArtifactRepositories();

        project.setDependencyArtifacts(dependencyArtifacts);

        final var resolutionResult = artifactResolver().resolveTransitively(
            dependencyArtifacts,
            projectArtifact,
            managedVersions,
            localRepository(),
            remoteArtifactRepositories,
            artifactMetadataSource(),
            candidate -> true,
            List.of(new WarningResolutionListener(container.getLoggerManager().getLoggerForComponent(ResolutionListener.class.getName())))
        );

        final var artifacts = resolutionResult.getArtifacts();

        project.setArtifacts(artifacts);
    }

    private void collectRecursive(@Nonnull MavenProject in, @Nonnull Set<MavenProject> target) {
        if (target.contains(in)) {
            return;
        }

        target.add(in);

        final var parent = in.getParent();
        if (parent != null) {
            collectRecursive(parent, target);
        }

        //noinspection unchecked
        final var modules = ((List<String>) in.getModules());
        if (modules != null) {
            for (final var module : modules) {
                final var path = in.getFile().toPath().getParent().resolve(module);
                try {
                    final var child = this.mavenProjectOf(path, false);
                    collectRecursive(child, target);
                } catch (RuntimeException e) {
                    LOG.atWarn()
                        .addKeyValue("project", in.getFile())
                        .addKeyValue("module", module)
                        .setCause(e)
                        .log("problems while evaluating module, this will be ignored");
                }
            }
        }
    }

    @Nonnull
    public Environment withUserProperties(@Nullable Map<String, String> v) {
        final var target = new Properties(userProperties());
        if (v != null) {
            target.putAll(v);
        }
        return new Environment(
            container(),
            settings(),
            localRepository(),
            target,
            executionProperties()
        );
    }

    @Nonnull
    public Environment withExecutionProperties(@Nullable Map<String, String> v) {
        final var target = new Properties(executionProperties());
        if (v != null) {
            target.putAll(v);
        }
        return new Environment(
            container(),
            settings(),
            localRepository(),
            userProperties(),
            target
        );
    }

}
