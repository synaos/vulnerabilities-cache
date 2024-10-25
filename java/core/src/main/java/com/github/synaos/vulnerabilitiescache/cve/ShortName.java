package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


public final class ShortName extends StringKind<ShortName> {

    @JsonCreator
    @Nonnull
    public static ShortName packageName(@Nonnull String v) {
        return new ShortName(v);
    }

    private ShortName(@Nonnull String value) {
        super(2, 32, value);
    }

}
