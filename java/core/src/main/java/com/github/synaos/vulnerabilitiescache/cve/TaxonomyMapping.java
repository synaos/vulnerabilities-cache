package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = TaxonomyMapping.Builder.class)
@ThreadSafe
@Immutable
public final class TaxonomyMapping {
    @Nonnull
    @JsonProperty("taxonomyName")
    private final TaxonomyName taxonomyName;
    @Nonnull
    @JsonProperty("taxonomyVersion")
    private final Optional<TaxonomyVersion> taxonomyVersion;
    @Nonnull
    @JsonProperty("taxonomyRelations")
    private final TaxonomyRelations taxonomyRelations;

    private TaxonomyMapping(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.taxonomyName = requireToBePresent(builder.taxonomyName, "taxonomyName");
        this.taxonomyVersion = builder.taxonomyVersion;
        this.taxonomyRelations = requireToBePresent(builder.taxonomyRelations, "taxonomyRelations");
    }

    @Nonnull
    public static Builder newTaxonomyMapping() {
        return new Builder();
    }

    @JsonProperty(value = "taxonomyName", required = true)
    @Nonnull
    public TaxonomyName taxonomyName() {
        return taxonomyName;
    }

    @JsonProperty("taxonomyVersion")
    @Nonnull
    public Optional<TaxonomyVersion> taxonomyVersion() {
        return taxonomyVersion;
    }

    @JsonProperty(value = "taxonomyRelations", required = true)
    @Nonnull
    public TaxonomyRelations taxonomyRelations() {
        return taxonomyRelations;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<TaxonomyName> taxonomyName = Optional.empty();
        @Nonnull
        private Optional<TaxonomyVersion> taxonomyVersion = Optional.empty();
        @Nonnull
        private Optional<TaxonomyRelations> taxonomyRelations = Optional.empty();

        @JsonProperty(value = "taxonomyName", required = true)
        @Nonnull
        public Builder withTaxonomyName(@Nonnull TaxonomyName v) {
            this.taxonomyName = ofNonNull(v, "taxonomyName");
            return this;
        }

        @JsonProperty("taxonomyVersion")
        @Nonnull
        public Builder withTaxonomyVersion(@Nullable TaxonomyVersion v) {
            this.taxonomyVersion = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "taxonomyRelations", required = true)
        @Nonnull
        public Builder withTaxonomyRelations(@Nonnull TaxonomyRelations v) {
            this.taxonomyRelations = ofNonNull(v, "taxonomyRelations");
            return this;
        }

        @Nonnull
        public TaxonomyMapping build() {
            return new TaxonomyMapping(this);
        }
    }
}
