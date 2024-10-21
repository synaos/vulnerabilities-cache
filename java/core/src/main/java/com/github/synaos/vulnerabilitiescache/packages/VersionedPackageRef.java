package com.github.synaos.vulnerabilitiescache.packages;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonEmpty;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static com.github.synaos.vulnerabilitiescache.packages.PackageRef.newPackageRef;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.common.Objects;
import com.github.synaos.vulnerabilitiescache.packages.PackageRef.Builder;
import com.github.synaos.vulnerabilitiescache.versions.Version;

@Immutable
@ThreadSafe
@JsonDeserialize(builder = Builder.class)
public final class VersionedPackageRef implements Comparable<VersionedPackageRef> {

    @Nonnull
    public static Builder newVersionedPackageRef() {
        return new Builder();
    }

    @Nonnull
    private final PackageRef ref;
    @Nonnull
    private final Version version;

    private VersionedPackageRef(@Nonnull Builder builder) {
        ref = builder.ref.build();
        version = requireToBePresent(builder.version, "version");
    }

    @Nonnull
    @JsonProperty("ref")
    public PackageRef ref() {
        return ref;
    }

    @Nonnull
    @JsonIgnore
    public PackageType type() {
        return ref.type();
    }

    @Nonnull
    @JsonIgnore
    public String name() {
        return ref.name();
    }

    @Nonnull
    @JsonProperty("version")
    public Version version() {
        return version;
    }

    @Override
    public String toString() {
        return ref + "/" + version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof final VersionedPackageRef that)) {return false;}
        return Objects.equals(ref, that.ref)
            && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref, version);
    }

    @Override
    public int compareTo(@Nonnull VersionedPackageRef o) {
        final var refDiff = ref.compareTo(o.ref);
        if (refDiff != 0) {
            return refDiff;
        }
        return version.compareTo(o.version);
    }

    @JsonPOJOBuilder
    public static final class Builder {

        @Nonnull
        private final PackageRef.Builder ref = newPackageRef();
        @Nonnull
        private Optional<Version> version = Optional.empty();

        @Nonnull
        public Builder copyOf(@Nonnull VersionedPackageRef base) {
            return withRef(base.ref)
                .withVersion(base.version);
        }

        @Nonnull
        @JsonProperty(value = "ref", required = true)
        public Builder withRef(@Nonnull PackageRef v) {
            ref.copyOf(requireNonNull(v, "ref"));
            return this;
        }

        @Nonnull
        @JsonIgnore
        public Builder withType(@Nonnull PackageType v) {
            ref.withType(v);
            return this;
        }

        @Nonnull
        @JsonIgnore
        public Builder withName(@Nonnull String v) {
            ref.withName(v);
            return this;
        }

        @Nonnull
        @JsonProperty(value = "version", required = true)
        public Builder withVersion(@Nonnull Version v) {
            version = ofNonNull(v, "version");
            return this;
        }

        @Nonnull
        @JsonIgnore
        public Builder withVersion(@Nonnull String v) {
            return withVersion(Version.of(requireNonEmpty(v, "version")));
        }

        @Nonnull
        public VersionedPackageRef build() {
            return new VersionedPackageRef(this);
        }
    }
}
