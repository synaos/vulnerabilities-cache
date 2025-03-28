package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class Version extends StringKind<Version> {

    @JsonCreator
    @Nonnull
    public static Version version(@Nonnull String v) {
        return new Version(v);
    }

    private Version(@Nonnull String value) {
        super(1, 1024, value);
    }

    @ThreadSafe
    @Immutable
    public final static class Type extends StringKind<Type> {

        @JsonCreator
        @Nonnull
        public static Type versionType(@Nonnull String v) {
            return new Type(v);
        }

        private Type(@Nonnull String value) {
            super(1, 128, value);
        }


    }
}
