package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class TaxonomyId extends StringKind<TaxonomyId> {

    @JsonCreator
    @Nonnull
    public static TaxonomyId taxonomyId(@Nonnull String v) {
        return new TaxonomyId(v);
    }

    private TaxonomyId(@Nonnull String value) {
        super(1, 2048, value);
    }

}
