package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CpeApplicability.Builder.class)
@ThreadSafe
@Immutable
public final class CpeApplicability {
    @Nonnull
    @JsonProperty("operator")
    private final Optional<Operator> operator;
    @JsonProperty("negate")
    private final boolean negate;
    @Nonnull
    @JsonProperty("nodes")
    private final CpeNodes nodes;

    private CpeApplicability(@Nonnull CpeApplicability.Builder builder) {
        requireNonNull(builder, "builder");
        this.operator = builder.operator;
        this.negate = builder.negate;
        this.nodes = requireToBePresent(builder.nodes, "nodes");
    }

    public enum Operator {
        AND,
        OR
    }

    @Nonnull
    @JsonProperty("operator")
    public Optional<Operator> operator() {
        return operator;
    }

    @JsonProperty("negate")
    public boolean negate() {
        return negate;
    }

    @Nonnull
    @JsonProperty("nodes")
    public CpeNodes nodes() {
        return nodes;
    }


    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Operator> operator = empty();
        private boolean negate = false;
        @Nonnull
        private Optional<CpeNodes> nodes = empty();

        @JsonProperty("operator")
        @Nonnull
        public Builder withOperator(@Nullable Operator v) {
            this.operator = ofNullable(v);
            return this;
        }

        @JsonProperty("negate")
        @Nonnull
        public Builder withNegate(boolean v) {
            this.negate = v;
            return this;
        }

        @JsonProperty(value = "nodes", required = true)
        @Nonnull
        public Builder withNodes(@Nonnull CpeNodes v) {
            this.nodes = ofNonNull(v, "nodes");
            return this;
        }

        @Nonnull
        public CpeApplicability build() {
            return new CpeApplicability(this);
        }
    }
}
