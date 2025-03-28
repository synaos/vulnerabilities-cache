package com.github.synaos.vulnerabilitiescache.cvss.v3;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Version {
    v3_0("3.0");

    @Nonnull
    private final String value;

    Version(@Nonnull String v) {
        this.value = v;
    }

    @Nonnull
    @JsonCreator
    public static Version of(@Nonnull String v) {
        for (final var candidate : values()) {
            if (candidate.value.equals(v)) {
                return candidate;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Nonnull
    @JsonValue
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
