package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class ReferenceName extends StringKind<ReferenceName> {

    @JsonCreator
    @Nonnull
    public static ReferenceName referenceName(@Nonnull String v) {
        return new ReferenceName(v);
    }

    private ReferenceName(@Nonnull String value) {
        super(1, 512, value);
    }

}
