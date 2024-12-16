package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class Title extends StringKind<Title> {

    @JsonCreator
    @Nonnull
    public static Title title(@Nonnull String v) {
        return new Title(v);
    }

    private Title(@Nonnull String value) {
        super(1, 256, value);
    }

}
