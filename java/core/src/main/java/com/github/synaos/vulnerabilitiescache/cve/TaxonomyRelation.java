package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

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

}
