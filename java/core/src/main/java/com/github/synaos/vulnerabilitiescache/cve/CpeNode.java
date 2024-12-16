package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.empty;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CpeNode.Builder.class)
@ThreadSafe
@Immutable
public final class CpeNode {
    @Nonnull
    @JsonProperty("operator")
    private final Operator operator;
    @JsonProperty("negate")
    private final boolean negate;
    @Nonnull
    @JsonProperty("cpeMatch")
    private final CpeMatches cpeMatch;

    private CpeNode(@Nonnull CpeNode.Builder builder) {
        requireNonNull(builder, "builder");
        this.operator = requireToBePresent(builder.operator, "operator");
        this.negate = builder.negate;
        this.cpeMatch = requireToBePresent(builder.cpeMatch, "cpeMatch");
    }

    public enum Operator {
        AND,
        OR
    }

    @Nonnull
    @JsonProperty("operator")
    public Operator operator() {
        return operator;
    }

    @JsonProperty("negate")
    public boolean negate() {
        return negate;
    }

    @Nonnull
    @JsonProperty("cpeMatch")
    public CpeMatches cpeMatch() {
        return cpeMatch;
    }


    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Operator> operator = empty();
        private boolean negate = false;
        @Nonnull
        private Optional<CpeMatches> cpeMatch = empty();

        @JsonProperty(value = "operator", required = true)
        @Nonnull
        public Builder withOperator(@Nonnull Operator v) {
            this.operator = ofNonNull(v, "operator");
            return this;
        }

        @JsonProperty("negate")
        @Nonnull
        public Builder withNegate(boolean v) {
            this.negate = v;
            return this;
        }

        @JsonProperty(value = "cpeMatch", required = true)
        @Nonnull
        public Builder withCpeMatch(@Nonnull CpeMatches v) {
            this.cpeMatch = ofNonNull(v, "cpeMatch");
            return this;
        }

        @Nonnull
        public CpeNode build() {
            return new CpeNode(this);
        }
    }
}
