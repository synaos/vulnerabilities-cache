package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


public final class PackageName extends StringKind<PackageName> {

    @JsonCreator
    @Nonnull
    public static PackageName packageName(@Nonnull String v) {
        return new PackageName(v);
    }

    private PackageName(@Nonnull String value) {
        super(1, 2048, value);
    }

}
