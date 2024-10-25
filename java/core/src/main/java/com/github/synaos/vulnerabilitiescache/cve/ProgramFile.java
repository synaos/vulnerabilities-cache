package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


public final class ProgramFile extends StringKind<ProgramFile> {

    @JsonCreator
    @Nonnull
    public static ProgramFile programFile(@Nonnull String v) {
        return new ProgramFile(v);
    }

    private ProgramFile(@Nonnull String value) {
        super(1, 1024, value);
    }

}
