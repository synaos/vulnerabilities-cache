package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.Id.Capec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = Impact.Builder.class)
@ThreadSafe
@Immutable
public final class Impact {
    @Nonnull
    @JsonProperty("capecId")
    private final Optional<Capec> capecId;
    @Nonnull
    @JsonProperty("descriptions")
    private final Descriptions descriptions;

    private Impact(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.capecId = builder.capecId;
        this.descriptions = requireToBePresent(builder.descriptions, "descriptions");
    }

    @Nonnull
    public static Builder newImpact() {
        return new Builder();
    }

    @JsonProperty("capecId")
    @Nonnull
    public Optional<Capec> capecId() {
        return capecId;
    }

    @JsonProperty(value = "descriptions", required = true)
    @Nonnull
    public Descriptions descriptions() {
        return descriptions;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Capec> capecId = Optional.empty();
        @Nonnull
        private Optional<Descriptions> descriptions = Optional.empty();

        @JsonProperty("capecId")
        @Nonnull
        public Builder withCapecId(@Nullable Capec v) {
            this.capecId = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "descriptions", required = true)
        @Nonnull
        public Builder withDescriptions(@Nonnull Descriptions v) {
            this.descriptions = ofNonNull(v, "descriptions");
            return this;
        }

        @Nonnull
        public Impact build() {
            return new Impact(this);
        }
    }
}
