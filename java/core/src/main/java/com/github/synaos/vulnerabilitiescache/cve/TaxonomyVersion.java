package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class TaxonomyVersion extends StringKind<TaxonomyVersion> {

    @JsonCreator
    @Nonnull
    public static TaxonomyVersion taxonomyVersion(@Nonnull String v) {
        return new TaxonomyVersion(v);
    }

    private TaxonomyVersion(@Nonnull String value) {
        super(1, 128, value);
    }

}
