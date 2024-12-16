package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class ModuleName extends StringKind<ModuleName> {

    @JsonCreator
    @Nonnull
    public static ModuleName moduleName(@Nonnull String v) {
        return new ModuleName(v);
    }

    private ModuleName(@Nonnull String value) {
        super(1, 4096, value);
    }

}
