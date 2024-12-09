package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class TaxonomyName extends StringKind<TaxonomyName> {

    @JsonCreator
    @Nonnull
    public static TaxonomyName taxonomyName(@Nonnull String v) {
        return new TaxonomyName(v);
    }

    private TaxonomyName(@Nonnull String value) {
        super(1, 128, value);
    }

}
