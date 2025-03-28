package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class ProgramName extends StringKind<ProgramName> {

    @JsonCreator
    @Nonnull
    public static ProgramName programName(@Nonnull String v) {
        return new ProgramName(v);
    }

    private ProgramName(@Nonnull String value) {
        super(1, 4096, value);
    }

}
