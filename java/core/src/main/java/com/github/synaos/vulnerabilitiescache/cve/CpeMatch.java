
package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.*;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CpeMatch.Builder.class)
@ThreadSafe
@Immutable
public final class CpeMatch {
    @JsonProperty("vulnerable")
    private final boolean vulnerable;
    @Nonnull
    @JsonProperty("criteria")
    private final Cpe23 criteria;
    @Nonnull
    @JsonProperty("matchCriteriaId")
    private final Optional<UUID> matchCriteriaId;
    @Nonnull
    @JsonProperty("versionStartExcluding")
    private final Optional<Version> versionStartExcluding;
    @Nonnull
    @JsonProperty("versionStartIncluding")
    private final Optional<Version> versionStartIncluding;
    @Nonnull
    @JsonProperty("versionEndExcluding")
    private final Optional<Version> versionEndExcluding;
    @Nonnull
    @JsonProperty("versionEndIncluding")
    private final Optional<Version> versionEndIncluding;

    private CpeMatch(@Nonnull CpeMatch.Builder builder) {
        requireNonNull(builder, "builder");
        this.vulnerable = requireToBePresent(builder.vulnerable, "vulnerable");
        this.criteria = requireToBePresent(builder.criteria, "criteria");
        this.matchCriteriaId = builder.matchCriteriaId;
        this.versionStartExcluding = builder.versionStartExcluding;
        this.versionStartIncluding = builder.versionStartIncluding;
        this.versionEndExcluding = builder.versionEndExcluding;
        this.versionEndIncluding = builder.versionEndIncluding;
    }

    @JsonProperty("vulnerable")
    public boolean vulnerable() {
        return vulnerable;
    }

    @Nonnull
    @JsonProperty("criteria")
    public Cpe23 criteria() {
        return criteria;
    }

    @Nonnull
    @JsonProperty("matchCriteriaId")
    public Optional<UUID> matchCriteriaId() {
        return matchCriteriaId;
    }

    @Nonnull
    @JsonProperty("versionStartExcluding")
    public Optional<Version> versionStartExcluding() {
        return versionStartExcluding;
    }

    @Nonnull
    @JsonProperty("versionStartIncluding")
    public Optional<Version> versionStartIncluding() {
        return versionStartIncluding;
    }

    @Nonnull
    @JsonProperty("versionEndExcluding")
    public Optional<Version> versionEndExcluding() {
        return versionEndExcluding;
    }

    @Nonnull
    @JsonProperty("versionEndIncluding")
    public Optional<Version> versionEndIncluding() {
        return versionEndIncluding;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Boolean> vulnerable = empty();
        @Nonnull
        private Optional<Cpe23> criteria = empty();
        @Nonnull
        private Optional<UUID> matchCriteriaId = empty();
        @Nonnull
        private Optional<Version> versionStartExcluding = empty();
        @Nonnull
        private Optional<Version> versionStartIncluding = empty();
        @Nonnull
        private Optional<Version> versionEndExcluding = empty();
        @Nonnull
        private Optional<Version> versionEndIncluding = empty();

        @JsonProperty(value = "vulnerable", required = true)
        @Nonnull
        public Builder withVulnerable(boolean v) {
            this.vulnerable = of(v);
            return this;
        }

        @JsonProperty(value = "criteria", required = true)
        @Nonnull
        public Builder withCriteria(@Nonnull Cpe23 v) {
            this.criteria = ofNonNull(v, "criteria");
            return this;
        }

        @JsonProperty("matchCriteriaId")
        @Nonnull
        public Builder withMatchCriteriaId(@Nullable UUID v) {
            this.matchCriteriaId = ofNullable(v);
            return this;
        }

        @JsonProperty("versionStartExcluding")
        @Nonnull
        public Builder withVersionStartExcluding(@Nullable Version v) {
            this.versionStartExcluding = ofNullable(v);
            return this;
        }

        @JsonProperty("versionStartIncluding")
        @Nonnull
        public Builder withVersionStartIncluding(@Nullable Version v) {
            this.versionStartIncluding = ofNullable(v);
            return this;
        }

        @JsonProperty("versionEndExcluding")
        @Nonnull
        public Builder withVersionEndExcluding(@Nullable Version v) {
            this.versionEndExcluding = ofNullable(v);
            return this;
        }

        @JsonProperty("versionEndIncluding")
        @Nonnull
        public Builder withVersionEndIncluding(@Nullable Version v) {
            this.versionEndIncluding = ofNullable(v);
            return this;
        }

        @Nonnull
        public CpeMatch build() {
            return new CpeMatch(this);
        }
    }
}
