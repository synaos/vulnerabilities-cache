package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class TaxonomyRelationshipName extends StringKind<TaxonomyRelationshipName> {

    @JsonCreator
    @Nonnull
    public static TaxonomyRelationshipName taxonomyRelationshipName(@Nonnull String v) {
        return new TaxonomyRelationshipName(v);
    }

    private TaxonomyRelationshipName(@Nonnull String value) {
        super(1, 128, value);
    }

}
