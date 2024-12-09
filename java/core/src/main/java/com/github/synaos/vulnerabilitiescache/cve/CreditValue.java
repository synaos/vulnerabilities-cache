package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class CreditValue extends StringKind<CreditValue> {

    @JsonCreator
    @Nonnull
    public static CreditValue creditValue(@Nonnull String v) {
        return new CreditValue(v);
    }

    private CreditValue(@Nonnull String value) {
        super(1, 4096, value);
    }

}
