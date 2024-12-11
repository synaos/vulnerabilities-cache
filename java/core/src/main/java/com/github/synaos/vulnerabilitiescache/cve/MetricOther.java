package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;

@JsonDeserialize(builder = MetricOther.Builder.class)
@ThreadSafe
@Immutable
public final class MetricOther {
    @Nonnull
    @JsonProperty("type")
    private final MetricOtherType type;

    private MetricOther(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.type = requireToBePresent(builder.type, "type");
    }

    @Nonnull
    public static Builder newMetricOther() {
        return new Builder();
    }

    @JsonProperty(value = "type", required = true)
    @Nonnull
    public MetricOtherType type() {
        return type;
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties({"content"})
    public static final class Builder {
        @Nonnull
        private Optional<MetricOtherType> type = Optional.empty();

        @JsonProperty(value = "type", required = true)
        @Nonnull
        public Builder withType(@Nonnull MetricOtherType v) {
            this.type = ofNonNull(v, "type");
            return this;
        }

        @Nonnull
        public MetricOther build() {
            return new MetricOther(this);
        }
    }
}
