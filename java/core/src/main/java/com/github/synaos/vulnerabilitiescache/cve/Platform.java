package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class Platform extends StringKind<Platform> {

    @JsonCreator
    @Nonnull
    public static Platform platform(@Nonnull String v) {
        return new Platform(v);
    }

    private Platform(@Nonnull String value) {
        super(1, 1024, value);
    }

}
