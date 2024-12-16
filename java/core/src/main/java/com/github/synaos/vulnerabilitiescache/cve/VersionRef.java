package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.cve.VersionRef.Builder;

@JsonDeserialize(builder = Builder.class)
@ThreadSafe
@Immutable
public final class VersionRef {
    @Nonnull
    @JsonProperty("version")
    private final Version version;
    @Nonnull
    @JsonProperty("status")
    private final Status status;
    @Nonnull
    @JsonProperty("versionType")
    private final Optional<Version.Type> versionType;
    @Nonnull
    @JsonProperty("lessThan")
    private final Optional<Version> lessThan;
    @Nonnull
    @JsonProperty("lessThanOrEqual")
    private final Optional<Version> lessThanOrEqual;
    @Nonnull
    @JsonProperty("changes")
    private final Optional<VersionChanges> changes;

    private VersionRef(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.version = requireToBePresent(builder.version, "version");
        this.status = requireToBePresent(builder.status, "status");
        this.versionType = builder.versionType;
        this.lessThan = builder.lessThan;
        this.lessThanOrEqual = builder.lessThanOrEqual;
        this.changes = builder.changes;
    }

    @Nonnull
    public static Builder newVersionRef() {
        return new Builder();
    }

    @JsonProperty(value = "version", required = true)
    @Nonnull
    public Version version() {
        return version;
    }

    @JsonProperty(value = "status", required = true)
    @Nonnull
    public Status status() {
        return status;
    }

    @JsonProperty("versionType")
    @Nonnull
    public Optional<Version.Type> versionType() {
        return versionType;
    }

    @JsonProperty("lessThan")
    @Nonnull
    public Optional<Version> lessThan() {
        return lessThan;
    }

    @JsonProperty("lessThanOrEqual")
    @Nonnull
    public Optional<Version> lessThanOrEqual() {
        return lessThanOrEqual;
    }

    @JsonProperty("changes")
    @Nonnull
    public Optional<VersionChanges> changes() {
        return changes;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Version> version = Optional.empty();
        @Nonnull
        private Optional<Status> status = Optional.empty();
        @Nonnull
        private Optional<Version.Type> versionType = Optional.empty();
        @Nonnull
        private Optional<Version> lessThan = Optional.empty();
        @Nonnull
        private Optional<Version> lessThanOrEqual = Optional.empty();
        @Nonnull
        private Optional<VersionChanges> changes = Optional.empty();

        @JsonProperty(value = "version", required = true)
        @Nonnull
        public Builder withVersion(@Nonnull Version v) {
            this.version = ofNonNull(v, "version");
            return this;
        }

        @JsonProperty(value = "status", required = true)
        @Nonnull
        public Builder withStatus(@Nonnull Status v) {
            this.status = ofNonNull(v, "status");
            return this;
        }

        @JsonProperty("versionType")
        @Nonnull
        public Builder withVersionType(@Nullable Version.Type v) {
            this.versionType = ofNullable(v);
            return this;
        }

        @JsonProperty("lessThan")
        @Nonnull
        public Builder withLessThan(@Nullable Version v) {
            this.lessThan = ofNullable(v);
            return this;
        }

        @JsonProperty("lessThanOrEqual")
        @Nonnull
        public Builder withLessThanOrEqual(@Nullable Version v) {
            this.lessThanOrEqual = ofNullable(v);
            return this;
        }

        @JsonProperty("changes")
        @Nonnull
        public Builder withChanges(@Nullable VersionChanges v) {
            this.changes = ofNullable(v);
            return this;
        }

        @Nonnull
        public VersionRef build() {
            return new VersionRef(this);
        }
    }
}
