
package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.cve.VersionChange.Builder;

@JsonDeserialize(builder = Builder.class)
@ThreadSafe
@Immutable
public final class VersionChange {
    @Nonnull
    @JsonProperty("at")
    private final Version at;
    @Nonnull
    @JsonProperty("status")
    private final Status status;

    private VersionChange(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.at = requireToBePresent(builder.at, "at");
        this.status = requireToBePresent(builder.status, "status");
    }

    @Nonnull
    public static Builder newVersionChange() {
        return new Builder();
    }

    @JsonProperty(value = "at", required = true)
    @Nonnull
    public Version at() {
        return at;
    }

    @JsonProperty(value = "status", required = true)
    @Nonnull
    public Status status() {
        return status;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Version> at = Optional.empty();
        @Nonnull
        private Optional<Status> status = Optional.empty();

        @JsonProperty(value = "at", required = true)
        @Nonnull
        public Builder withAt(@Nonnull Version v) {
            this.at = ofNonNull(v, "at");
            return this;
        }

        @JsonProperty(value = "status", required = true)
        @Nonnull
        public Builder withDescription(@Nonnull Status v) {
            this.status = ofNonNull(v, "status");
            return this;
        }

        @Nonnull
        public VersionChange build() {
            return new VersionChange(this);
        }
    }
}
