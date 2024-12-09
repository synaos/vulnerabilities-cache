package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class SupportedMediaType extends StringKind<SupportedMediaType> {

    @JsonCreator
    @Nonnull
    public static SupportedMediaType supportedMediaType(@Nonnull String v) {
        return new SupportedMediaType(v);
    }

    private SupportedMediaType(@Nonnull String value) {
        super(1, 256, value);
    }

}
