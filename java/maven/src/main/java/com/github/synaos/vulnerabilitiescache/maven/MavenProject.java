package com.github.synaos.vulnerabilitiescache.maven;


import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static com.github.synaos.vulnerabilitiescache.dependencies.Dependency.Scope.project;
import static com.github.synaos.vulnerabilitiescache.maven.Environment.environment;
import static com.github.synaos.vulnerabilitiescache.packages.PackageType.maven;
import static com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef.newVersionedPackageRef;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.github.synaos.vulnerabilitiescache.common.Lazy;
import com.github.synaos.vulnerabilitiescache.dependencies.Dependencies;
import com.github.synaos.vulnerabilitiescache.dependencies.Dependency;
import com.github.synaos.vulnerabilitiescache.dependencies.Owner;
import com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.ModelBase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Immutable
@ThreadSafe
public final class MavenProject implements Dependency, Dependencies, Owner {

    @Nonnull
    public static Builder newMavenProject() {
        return newMavenProject(environment());
    }

    @Nonnull
    public static Builder newMavenProject(@Nonnull Environment environment) {
        return new Builder(requireNonNull(environment, "environment"));
    }

    @Nonnull
    private final org.apache.maven.project.MavenProject raw;
    private final boolean withDependencies;
    @Nonnull
    private final Environment environment;
    @Nonnull
    private final Lazy<org.apache.maven.project.MavenProject, VersionedPackageRef> ref = new Lazy<>(raw ->
        newVersionedPackageRef()
            .withType(maven)
            .withName(raw.getGroupId() + ":" + raw.getArtifactId())
            .withVersion(raw.getVersion())
            .build()
    );

    private MavenProject(@Nonnull Builder builder) {
        raw = requireToBePresent(builder.mavenProject, "raw");
        withDependencies = builder.withDependencies;
        environment = builder.environment;
    }

    @Nonnull
    public org.apache.maven.project.MavenProject raw() {
        try {
            return (org.apache.maven.project.MavenProject) raw.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    public Flux<MavenProject> modules(boolean withDependencies, boolean recursive) {
        //noinspection unchecked
        final var modules = (List<String>) raw.getModules();
        if (modules == null || modules.isEmpty()) {
            return Flux.empty();
        }
        final var modulesResolved = Flux.fromIterable(modules)
            .map(moduleName -> raw.getFile().toPath().getParent().resolve(moduleName))
            .map(path -> newMavenProject(environment)
                .withPomXml(path, withDependencies)
                .build()
            );
        if (!recursive) {
            return modulesResolved;
        }
        return modulesResolved
            .flatMap(module -> Flux.just(module).concatWith(module.modules(withDependencies, true)));
    }

    @Nonnull
    @Override
    public VersionedPackageRef ref() {
        return ref.get(raw);
    }

    @Nonnull
    @Override
    public Scope scope() {
        return project;
    }

    @Nonnull
    @Override
    public Flux<Dependency> dependencies() {
        if (withDependencies) {
            //noinspection unchecked
            final Collection<Artifact> artifacts = (Collection<Artifact>) raw.getArtifacts();
            if (artifacts == null || artifacts.isEmpty()) {
                return Flux.empty();
            }
            return Flux.fromIterable(new TreeSet<>(artifacts))
                .map(v -> MavenDependency.of(this, v));
        }
        return Mono.justOrEmpty(raw.getModel())
            .flatMapIterable(ModelBase::getDependencies)
            .map(v -> MavenDependency.of(this, v));
    }

    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass", "EqualsDoesntCheckParameterClass"})
    @Override
    public boolean equals(Object o) {
        return Dependency.equals(this, o);
    }

    @Override
    public int hashCode() {
        return Dependency.hashCode(this);
    }

    @Override
    public String toString() {
        return Dependency.toString(this);
    }

    public static final class Builder {

        @Nonnull
        private final Environment environment;
        @Nonnull
        private Optional<org.apache.maven.project.MavenProject> mavenProject = Optional.empty();
        private boolean withDependencies;

        public Builder(@Nonnull Environment environment) {
            this.environment = requireNonNull(environment, "environment");
        }

        @Nonnull
        public Builder withMavenProject(@Nonnull org.apache.maven.project.MavenProject v) {
            mavenProject = ofNonNull(v, "mavenProject");
            return this;
        }

        @Nonnull
        public Builder withPomXml(@Nonnull Path v) {
            return withPomXml(v, true);
        }

        @Nonnull
        public Builder withPomXml(@Nonnull Path v, boolean withDependencies) {
            requireNonNull(v, "pomXml");
            this.withDependencies = withDependencies;
            return withMavenProject(environment.mavenProjectOf(v, withDependencies));
        }

        @Nonnull
        public MavenProject build() {
            return new MavenProject(this);
        }

        @Nonnull
        public Flux<MavenProject> buildRecursive() {
            final var root = build();
            return Flux.concat(
                Flux.just(root),
                root.modules(withDependencies, true)
            );
        }

    }

}
