package com.github.synaos.vulnerabilitiescache.packages;

import static com.github.synaos.vulnerabilitiescache.common.Optionals.*;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.common.Objects;
import com.github.synaos.vulnerabilitiescache.packages.PackageRef.Builder;

@Immutable
@ThreadSafe
@JsonDeserialize(builder = Builder.class)
public final class PackageRef implements Comparable<PackageRef> {

    @Nonnull
    public static Builder newPackageRef() {
        return new Builder();
    }

    @Nonnull
    private final PackageType type;
    @Nonnull
    private final String name;

    private PackageRef(@Nonnull Builder builder) {
        type = requireToBePresent(builder.type, "type");
        name = requireToBePresent(builder.name, "name");
    }

    @Nonnull
    @JsonProperty("type")
    public PackageType type() {
        return type;
    }

    @Nonnull
    @JsonProperty("name")
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return type + "/" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof final PackageRef that)) {return false;}
        return type == that.type && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public int compareTo(@Nonnull PackageRef o) {
        final var typeDiff = type.compareTo(o.type);
        if (typeDiff != 0) {
            return typeDiff;
        }
        return name.compareTo(o.name);
    }

    @JsonPOJOBuilder
    public static final class Builder {

        @Nonnull
        private Optional<PackageType> type = Optional.empty();
        @Nonnull
        private Optional<String> name = Optional.empty();

        @Nonnull
        public Builder copyOf(@Nonnull PackageRef base) {
            return withType(base.type)
                .withName(base.name);
        }

        @Nonnull
        @JsonProperty(value = "type", required = true)
        public Builder withType(@Nonnull PackageType v) {
            type = ofNonNull(v, "v");
            return this;
        }

        @Nonnull
        @JsonProperty(value = "name", required = true)
        public Builder withName(@Nonnull String v) {
            name = ofNonEmpty(v, "name");
            return this;
        }

        @Nonnull
        public PackageRef build() {
            return new PackageRef(this);
        }
    }
}
