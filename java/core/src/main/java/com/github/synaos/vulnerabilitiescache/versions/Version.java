package com.github.synaos.vulnerabilitiescache.versions;

import static com.github.zafarkhaja.semver.Version.parse;
import static com.github.zafarkhaja.semver.Version.tryParse;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonEmpty;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.versions.Version.Type.generic;
import static com.github.synaos.vulnerabilitiescache.versions.Version.Type.semver;

import java.util.Optional;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.synaos.vulnerabilitiescache.common.Objects;

public interface Version extends Comparable<Version> {

    @Nonnull
    @JsonCreator
    static Version of(@Nonnull String v) {
        return tryParse(v)
            .<Version>map(Semver::new)
            .orElseGet(() -> genericOf(v));
    }

    @Nonnull
    static Generic genericOf(@Nonnull String v) {
        return new Generic(requireNonEmpty(v, "version"));
    }

    @Nonnull
    static Semver semverOf(@Nonnull String v) {
        final var parsed = parse(requireNonEmpty(v, "version"));

        return new Semver(parsed);
    }

    @Nonnull
    @JsonValue
    String name();

    @Nonnull
    Type type();

    @Override
    default int compareTo(@Nonnull Version o) {
        return name().compareTo(o.name());
    }

    enum Type {
        generic,
        semver,
    }

    final class Generic implements Version {

        @Nonnull
        private final String name;

        private Generic(@Nonnull String name) {
            this.name = requireNonEmpty(name, "name");
        }

        @Nonnull
        @Override
        public String name() {
            return name;
        }

        @Nonnull
        @Override
        public Type type() {
            return generic;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {return true;}
            if (o instanceof final Version other) {
                return Objects.equals(name, other.name());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return name();
        }
    }

    final class Semver implements Version {

        @Nonnull
        private final com.github.zafarkhaja.semver.Version bare;

        private Semver(@Nonnull com.github.zafarkhaja.semver.Version bare) {
            this.bare = requireNonNull(bare, "bare");
        }

        @Nonnull
        @Override
        public String name() {
            return bare.toString();
        }

        @Nonnegative
        public long majorVersion() {
            return bare.majorVersion();
        }

        @Nonnegative
        public long minorVersion() {
            return bare.minorVersion();
        }

        @Nonnegative
        public long patchVersion() {
            return bare.patchVersion();
        }

        @Nonnull
        public Optional<String> preReleaseVersion() {
            return bare.preReleaseVersion();
        }

        @Nonnull
        public Optional<String> buildMetadata() {
            return bare.buildMetadata();
        }

        @Nonnull
        @Override
        public Type type() {
            return semver;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {return true;}
            if (o instanceof final Semver other) {
                return Objects.equals(bare, other.bare);
            }
            if (o instanceof final Version other) {
                return Objects.equals(name(), other.name());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(bare);
        }

        @Override
        public String toString() {
            return name();
        }
    }


}
