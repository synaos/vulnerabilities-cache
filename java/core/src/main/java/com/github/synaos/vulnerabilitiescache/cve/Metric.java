package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.cvss.v2.Cvss;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = Metric.Builder.class)
@ThreadSafe
@Immutable
public final class Metric {
    @Nonnull
    @JsonProperty("format")
    private final Optional<MetricFormat> format;
    @Nonnull
    @JsonProperty("scenarios")
    private final Optional<MetricScenarios> scenarios;
    @Nonnull
    @JsonProperty("cvssV2_0")
    private final Optional<Cvss> cvssV2_0;
    @Nonnull
    @JsonProperty("cvssV3_0")
    private final Optional<com.github.synaos.vulnerabilitiescache.cvss.v3.Cvss> cvssV3_0;
    @Nonnull
    @JsonProperty("cvssV3_1")
    private final Optional<com.github.synaos.vulnerabilitiescache.cvss.v3d1.Cvss> cvssV3_1;
    @Nonnull
    @JsonProperty("cvssV4_0")
    private final Optional<com.github.synaos.vulnerabilitiescache.cvss.v4.Cvss> cvssV4_0;
    @Nonnull
    @JsonProperty("other")
    private final Optional<MetricOther> other;

    private Metric(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.format = builder.format;
        this.scenarios = builder.scenarios;
        this.cvssV2_0 = builder.cvssV2_0;
        this.cvssV3_0 = builder.cvssV3_0;
        this.cvssV3_1 = builder.cvssV3_1;
        this.cvssV4_0 = builder.cvssV4_0;
        this.other = builder.other;
    }

    @Nonnull
    public static Builder newMetric() {
        return new Builder();
    }

    @JsonProperty("format")
    @Nonnull
    public Optional<MetricFormat> format() {
        return format;
    }

    @JsonProperty("scenarios")
    @Nonnull
    public Optional<MetricScenarios> scenarios() {
        return scenarios;
    }

    @JsonProperty("cvssV2_0")
    @Nonnull
    public Optional<Cvss> cvssV2_0() {
        return cvssV2_0;
    }

    @JsonProperty("cvssV3_0")
    @Nonnull
    public Optional<com.github.synaos.vulnerabilitiescache.cvss.v3.Cvss> cvssV3_0() {
        return cvssV3_0;
    }

    @JsonProperty("cvssV3_1")
    @Nonnull
    public Optional<com.github.synaos.vulnerabilitiescache.cvss.v3d1.Cvss> cvssV3_1() {
        return cvssV3_1;
    }

    @JsonProperty("cvssV4_0")
    @Nonnull
    public Optional<com.github.synaos.vulnerabilitiescache.cvss.v4.Cvss> cvssV4_0() {
        return cvssV4_0;
    }

    @JsonProperty("other")
    @Nonnull
    public Optional<MetricOther> other() {
        return other;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<MetricFormat> format = Optional.empty();
        @Nonnull
        private Optional<MetricScenarios> scenarios = Optional.empty();
        @Nonnull
        private Optional<com.github.synaos.vulnerabilitiescache.cvss.v2.Cvss> cvssV2_0 = Optional.empty();
        @Nonnull
        private Optional<com.github.synaos.vulnerabilitiescache.cvss.v3.Cvss> cvssV3_0 = Optional.empty();
        @Nonnull
        private Optional<com.github.synaos.vulnerabilitiescache.cvss.v3d1.Cvss> cvssV3_1 = Optional.empty();
        @Nonnull
        private Optional<com.github.synaos.vulnerabilitiescache.cvss.v4.Cvss> cvssV4_0 = Optional.empty();
        @Nonnull
        private Optional<MetricOther> other = Optional.empty();

        @JsonProperty("format")
        @Nonnull
        public Builder withFormat(@Nullable MetricFormat v) {
            this.format = ofNullable(v);
            return this;
        }

        @JsonProperty("scenarios")
        @Nonnull
        public Builder withScenarios(@Nullable MetricScenarios v) {
            this.scenarios = ofNullable(v);
            return this;
        }

        @JsonProperty("cvssV2_0")
        @Nonnull
        public Builder withCvssV2_0(@Nullable com.github.synaos.vulnerabilitiescache.cvss.v2.Cvss v) {
            this.cvssV2_0 = ofNullable(v);
            return this;
        }

        @JsonProperty("cvssV3_0")
        @Nonnull
        public Builder withCvssV3_0(@Nullable com.github.synaos.vulnerabilitiescache.cvss.v3.Cvss v) {
            this.cvssV3_0 = ofNullable(v);
            return this;
        }

        @JsonProperty("cvssV3_1")
        @Nonnull
        public Builder withCvssV3_1(@Nullable com.github.synaos.vulnerabilitiescache.cvss.v3d1.Cvss v) {
            this.cvssV3_1 = ofNullable(v);
            return this;
        }

        @JsonProperty("cvssV4_0")
        @Nonnull
        public Builder withCvssV4_0(@Nullable com.github.synaos.vulnerabilitiescache.cvss.v4.Cvss v) {
            this.cvssV4_0 = ofNullable(v);
            return this;
        }

        @JsonProperty("other")
        @Nonnull
        public Builder withOther(@Nullable MetricOther v) {
            this.other = ofNullable(v);
            return this;
        }

        @Nonnull
        public Metric build() {
            return new Metric(this);
        }
    }
}
