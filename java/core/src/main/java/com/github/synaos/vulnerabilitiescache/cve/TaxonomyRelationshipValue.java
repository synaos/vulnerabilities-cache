package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class TaxonomyRelationshipValue extends StringKind<TaxonomyRelationshipValue> {

    @JsonCreator
    @Nonnull
    public static TaxonomyRelationshipValue taxonomyRelationshipValue(@Nonnull String v) {
        return new TaxonomyRelationshipValue(v);
    }

    private TaxonomyRelationshipValue(@Nonnull String value) {
        super(1, 2048, value);
    }

}
