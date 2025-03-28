package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class SupportedMediaValue extends StringKind<SupportedMediaValue> {

    @JsonCreator
    @Nonnull
    public static SupportedMediaValue supportedMediaValue(@Nonnull String v) {
        return new SupportedMediaValue(v);
    }

    private SupportedMediaValue(@Nonnull String value) {
        super(1, 16384, value);
    }

}
