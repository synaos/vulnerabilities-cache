package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Locale;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;

@JsonDeserialize(builder = MetricScenario.Builder.class)
@ThreadSafe
@Immutable
public final class MetricScenario {
    @Nonnull
    @JsonProperty("lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty("value")
    private final MetricScenarioValue value;

    private MetricScenario(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.lang = requireToBePresent(builder.lang, "lang");
        this.value = requireToBePresent(builder.value, "value");
    }

    @Nonnull
    public static Builder newMetricScenario() {
        return new Builder();
    }

    @JsonProperty(value = "lang", required = true)
    @Nonnull
    public Locale lang() {
        return lang;
    }

    @JsonProperty(value = "value", required = true)
    @Nonnull
    public MetricScenarioValue value() {
        return value;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Locale> lang = Optional.empty();
        @Nonnull
        private Optional<MetricScenarioValue> value = Optional.empty();

        @JsonProperty(value = "lang", required = true)
        @Nonnull
        public Builder withLang(@Nonnull Locale v) {
            this.lang = ofNonNull(v, "lang");
            return this;
        }

        @JsonProperty(value = "value", required = true)
        @Nonnull
        public Builder withValue(@Nonnull MetricScenarioValue v) {
            this.value = ofNonNull(v, "value");
            return this;
        }

        @Nonnull
        public MetricScenario build() {
            return new MetricScenario(this);
        }
    }
}
