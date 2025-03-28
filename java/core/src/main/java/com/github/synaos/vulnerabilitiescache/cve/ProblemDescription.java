package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.Id.Cwe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Locale;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = ProblemDescription.Builder.class)
@ThreadSafe
@Immutable
public final class ProblemDescription {
    @Nonnull
    @JsonProperty("lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty("description")
    private final ProblemDescriptionText description;
    @Nonnull
    @JsonProperty("cweId")
    private final Optional<Cwe> cweId;
    @Nonnull
    @JsonProperty("type")
    private final Optional<ProblemDescriptionType> type;
    @Nonnull
    @JsonProperty("references")
    private final Optional<References> references;

    private ProblemDescription(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.lang = requireToBePresent(builder.lang, "lang");
        this.description = requireToBePresent(builder.description, "description");
        this.cweId = builder.cweId;
        this.type = builder.type;
        this.references = builder.references;
    }

    @Nonnull
    public static Builder newProblemDescription() {
        return new Builder();
    }

    @JsonProperty(value = "lang", required = true)
    @Nonnull
    public Locale lang() {
        return lang;
    }

    @JsonProperty(value = "description", required = true)
    @Nonnull
    public ProblemDescriptionText description() {
        return description;
    }

    @JsonProperty("cweId")
    @Nonnull
    public Optional<Cwe> cweId() {
        return cweId;
    }

    @JsonProperty("type")
    @Nonnull
    public Optional<ProblemDescriptionType> type() {
        return type;
    }

    @JsonProperty("references")
    @Nonnull
    public Optional<References> references() {
        return references;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Locale> lang = Optional.empty();
        @Nonnull
        private Optional<ProblemDescriptionText> description = Optional.empty();
        @Nonnull
        private Optional<Cwe> cweId = Optional.empty();
        @Nonnull
        private Optional<ProblemDescriptionType> type = Optional.empty();
        @Nonnull
        private Optional<References> references = Optional.empty();

        @JsonProperty(value = "lang", required = true)
        @Nonnull
        public Builder withLang(@Nonnull Locale v) {
            this.lang = ofNonNull(v, "lang");
            return this;
        }

        @JsonProperty(value = "description", required = true)
        @Nonnull
        public Builder withDescription(@Nonnull ProblemDescriptionText v) {
            this.description = ofNonNull(v, "description");
            return this;
        }

        @JsonProperty("cweId")
        @Nonnull
        public Builder withCweId(@Nullable Cwe v) {
            this.cweId = ofNullable(v);
            return this;
        }

        @JsonProperty("type")
        @Nonnull
        public Builder withType(@Nullable ProblemDescriptionType v) {
            this.type = ofNullable(v);
            return this;
        }

        @JsonProperty("references")
        @Nonnull
        public Builder withReferences(@Nullable References v) {
            this.references = ofNullable(v);
            return this;
        }

        @Nonnull
        public ProblemDescription build() {
            return new ProblemDescription(this);
        }
    }
}
