package com.github.synaos.vulnerabilitiescache.common;

import static java.lang.String.format;

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Objects {

    @Nonnull
    static <T extends CharSequence> T requireNonEmpty(@Nullable T in, @Nonnull String name) {
        requireNonNull(in, name);
        if (in.isEmpty()) {
            throw new IllegalArgumentException(format("'%s' required but is empty", name));
        }
        return in;
    }

    @Nonnull
    static <T extends Collection<?>> T requireNonEmpty(@Nullable T in, @Nonnull String name) {
        requireNonNull(in, name);
        if (in.isEmpty()) {
            throw new IllegalArgumentException(format("'%s' required but is empty", name));
        }
        return in;
    }

    @Nonnull
    static <T extends Iterable<?>> T requireNonEmpty(@Nullable T in, @Nonnull String name) {
        requireNonNull(in, name);
        if (!in.iterator().hasNext()) {
            throw new IllegalArgumentException(format("'%s' required but is empty", name));
        }
        return in;
    }

    @Nonnull
    static <T> T requireNonNull(@Nullable T in, @Nonnull String name) {
        if (in == null) {
            throw new IllegalArgumentException(format("'%s' required but is null", name));
        }
        return in;
    }

    @Nonnull
    static <T extends Number> T requireNonNegative(@Nullable T in, @Nonnull String name) {
        requireNonNull(in, name);
        if (in.doubleValue() < 0) {
            throw new IllegalArgumentException(format("'%s' required to be not negative but got: %s", name, in));
        }
        return in;
    }

    static int hash(@Nullable Object... values) {
        return java.util.Objects.hash(values);
    }

    static boolean equals(@Nullable Object left, @Nullable Object right) {
        return java.util.Objects.equals(left, right);
    }

    static boolean compare(@Nullable Object left, @Nullable Object right) {
        return java.util.Objects.equals(left, right);
    }

}
