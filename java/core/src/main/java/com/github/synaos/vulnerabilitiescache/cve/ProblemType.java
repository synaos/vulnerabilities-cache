package com.github.synaos.vulnerabilitiescache.cve;

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

@JsonDeserialize(builder = ProblemType.Builder.class)
@ThreadSafe
@Immutable
public final class ProblemType {
    @JsonProperty("descriptions")
    private final ProblemDescriptions descriptions;

    private ProblemType(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.descriptions = requireToBePresent(builder.descriptions, "descriptions");
    }

    @Nonnull
    public static Builder newProblemType() {
        return new Builder();
    }

    @JsonProperty(value = "descriptions", required = true)
    @Nonnull
    public ProblemDescriptions descriptions() {
        return descriptions;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        private Optional<ProblemDescriptions> descriptions = Optional.empty();

        @JsonProperty(value = "descriptions", required = true)
        @Nonnull
        public Builder withDescriptions(@Nonnull ProblemDescriptions v) {
            this.descriptions = ofNonNull(v, "descriptions");
            return this;
        }

        @Nonnull
        public ProblemType build() {
            return new ProblemType(this);
        }
    }
}
