package com.github.synaos.vulnerabilitiescache.common;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonEmpty;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNegative;
import static java.lang.String.format;
import static java.util.Optional.of;

import java.util.Collection;
import java.util.Optional;
import javax.annotation.Nonnull;

public interface Optionals {

    @Nonnull
    static <T> T requireToBePresent(@Nonnull Optional<T> of, @Nonnull String name) {
        return of
            .orElseThrow(() -> new IllegalArgumentException(format("'%s' required but is absent", name)));
    }

    @Nonnull
    static <T> Optional<T> ofNonNull(@Nonnull T v, @Nonnull String name) {
        //noinspection ConstantConditions
        if (v == null) {
            throw new IllegalArgumentException(format("'%s' required but is absent", name));
        }
        return of(v);
    }

    @Nonnull
    static <T extends CharSequence> Optional<T> ofNonEmpty(@Nonnull T v, @Nonnull String name) {
        final var result = ofNonNull(v, name);
        requireNonEmpty(v, name);
        return result;
    }

    @Nonnull
    static <T extends Collection<?>> Optional<T> ofNonEmpty(@Nonnull T v, @Nonnull String name) {
        final var result = ofNonNull(v, name);
        requireNonEmpty(v, name);
        return result;
    }

    @Nonnull
    static <T extends Iterable<?>> Optional<T> ofNonEmpty(@Nonnull T v, @Nonnull String name) {
        final var result = ofNonNull(v, name);
        requireNonEmpty(v, name);
        return result;
    }

    @Nonnull
    static <T extends Number> Optional<T> ofNonNegative(@Nonnull T v, @Nonnull String name) {
        final var result = ofNonNull(v, name);
        requireNonNegative(v, name);
        return result;
    }

}
