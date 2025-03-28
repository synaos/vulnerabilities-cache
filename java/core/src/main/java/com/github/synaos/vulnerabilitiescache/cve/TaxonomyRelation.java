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

@JsonDeserialize(builder = TaxonomyRelation.Builder.class)
@ThreadSafe
@Immutable
public final class TaxonomyRelation {
    @Nonnull
    @JsonProperty("taxonomyId")
    private final TaxonomyId taxonomyId;
    @Nonnull
    @JsonProperty("relationshipName")
    private final TaxonomyRelationshipName relationshipName;
    @Nonnull
    @JsonProperty("relationshipValue")
    private final TaxonomyRelationshipValue relationshipValue;

    private TaxonomyRelation(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.taxonomyId = requireToBePresent(builder.taxonomyId, "taxonomyId");
        this.relationshipName = requireToBePresent(builder.relationshipName, "relationshipName");
        this.relationshipValue = requireToBePresent(builder.relationshipValue, "relationshipValue");
    }


    @Nonnull
    public static Builder newTaxonomyRelation() {
        return new Builder();
    }

    @JsonProperty(value = "taxonomyId", required = true)
    @Nonnull
    public TaxonomyId taxonomyId() {
        return taxonomyId;
    }

    @JsonProperty(value = "relationshipName", required = true)
    @Nonnull
    public TaxonomyRelationshipName relationshipName() {
        return relationshipName;
    }

    @JsonProperty(value = "relationshipValue", required = true)
    @Nonnull
    public TaxonomyRelationshipValue relationshipValue() {
        return relationshipValue;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<TaxonomyId> taxonomyId = Optional.empty();
        @Nonnull
        private Optional<TaxonomyRelationshipName> relationshipName = Optional.empty();
        @Nonnull
        private Optional<TaxonomyRelationshipValue> relationshipValue = Optional.empty();

        @JsonProperty(value = "taxonomyId", required = true)
        @Nonnull
        public Builder withTaxonomyId(@Nonnull TaxonomyId v) {
            this.taxonomyId = ofNonNull(v, "taxonomyId");
            return this;
        }

        @JsonProperty(value = "relationshipName", required = true)
        @Nonnull
        public Builder withRelationshipName(@Nonnull TaxonomyRelationshipName v) {
            this.relationshipName = ofNonNull(v, "relationshipName");
            return this;
        }

        @JsonProperty(value = "relationshipValue", required = true)
        @Nonnull
        public Builder withRelationshipValue(@Nonnull TaxonomyRelationshipValue v) {
            this.relationshipValue = ofNonNull(v, "relationshipValue");
            return this;
        }

        @Nonnull
        public TaxonomyRelation build() {
            return new TaxonomyRelation(this);
        }
    }
}
