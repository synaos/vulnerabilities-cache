package com.github.synaos.vulnerabilitiescache.maven;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.dependencies.Dependency.Scope.*;
import static com.github.synaos.vulnerabilitiescache.packages.PackageType.maven;
import static com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef.newVersionedPackageRef;
import static java.util.Optional.ofNullable;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.github.synaos.vulnerabilitiescache.common.Lazy;
import com.github.synaos.vulnerabilitiescache.dependencies.Dependency;
import com.github.synaos.vulnerabilitiescache.dependencies.Owner;
import com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef;
import org.jetbrains.annotations.NotNull;

@Immutable
@ThreadSafe
public final class MavenDependency<R> implements Dependency {

    @Nonnull
    private final MavenProject owner;
    @Nonnull
    private final R raw;
    @Nonnull
    private final Lazy<R, VersionedPackageRef> ref;
    @Nonnull
    private final Lazy<R, Scope> scope;

    MavenDependency(
        @Nonnull MavenProject owner,
        @Nonnull R raw,
        @Nonnull Function<R, VersionedPackageRef> refFactory,
        @Nonnull Function<R, Scope> scopeFactory
    ) {
        this.owner = requireNonNull(owner, "owner");
        this.raw = requireNonNull(raw, "raw");
        this.ref = new Lazy<>(refFactory);
        this.scope = new Lazy<>(scopeFactory);
    }

    @Nonnull
    @Override
    public VersionedPackageRef ref() {
        return ref.get(raw);
    }

    @Nonnull
    @Override
    public Scope scope() {
        return scope.get(raw);
    }

    @NotNull
    @Override
    public Optional<Owner> owner() {
        return Optional.of(owner);
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

    @Nonnull
    static MavenDependency<org.apache.maven.model.Dependency> of(@Nonnull MavenProject owner, @Nonnull org.apache.maven.model.Dependency v) {
        return new MavenDependency<>(
            requireNonNull(owner, "owner"),
            requireNonNull(v, "dependency"),
            raw -> newVersionedPackageRef()
                .withType(maven)
                .withName(resolveNameOf(raw::getGroupId, raw::getArtifactId, raw::getClassifier, raw::getType))
                .withVersion(raw.getVersion())
                .build(),
            raw -> resolveScopeOf(raw.getScope())
        );
    }

    @Nonnull
    static MavenDependency<org.apache.maven.artifact.Artifact> of(@Nonnull MavenProject owner, @Nonnull org.apache.maven.artifact.Artifact v) {
        requireNonNull(v, "artifact");
        return new MavenDependency<>(
            requireNonNull(owner, "owner"),
            requireNonNull(v, "dependency"),
            raw -> newVersionedPackageRef()
                .withType(maven)
                .withName(resolveNameOf(raw::getGroupId, raw::getArtifactId, raw::getClassifier, raw::getType))
                .withVersion(raw.getVersion())
                .build(),
            raw -> resolveScopeOf(raw.getScope())
        );
    }

    @Nonnull
    private static String resolveNameOf(
        @Nonnull Supplier<String> groupIdS,
        @Nonnull Supplier<String> artifactIdS,
        @Nonnull Supplier<String> classifierS,
        @Nonnull Supplier<String> typeS
    ) {
        final var groupId = requireNonNull(requireNonNull(groupIdS, "groupIdS").get(), "groupId");
        final var artifactId = requireNonNull(requireNonNull(artifactIdS, "artifactIdS").get(), "artifactId");
        final var classifier = requireNonNull(classifierS, "classifierS").get();
        final var type = requireNonNull(requireNonNull(typeS, "typeS").get(), "type");

        final var sb = new StringBuilder()
            .append(groupId).append(':')
            .append(artifactId).append(':');

        if (classifier != null && !classifier.isEmpty()) {
            sb.append(classifier).append(':');
        }

        sb.append(type).append(':');

        return sb.toString();
    }

    @Nonnull
    private static Scope resolveScopeOf(@Nullable String plain) {
        return ofNullable(plain)
            .map(v -> switch (v) {
                case "compile" -> production;
                case "test" -> testing;
                default -> other;
            })
            .orElse(production);
    }

}
