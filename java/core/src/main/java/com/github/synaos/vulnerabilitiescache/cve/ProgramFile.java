package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


@ThreadSafe
@Immutable
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
