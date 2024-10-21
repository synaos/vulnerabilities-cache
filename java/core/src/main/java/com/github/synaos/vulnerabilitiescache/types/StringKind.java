package com.github.synaos.vulnerabilitiescache.types;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNegative;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import java.util.Optional;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.github.synaos.vulnerabilitiescache.common.Objects;

public abstract class StringKind<T extends StringKind<T>> implements Comparable<T> {

    @Nonnegative
    private final Optional<Integer> minLength;
    @Nonnegative
    private final Optional<Integer> maxLength;
    @Nonnull
    private final String value;

    protected StringKind(
        @Nullable @Nonnegative Integer minLength,
        @Nullable @Nonnegative Integer maxLength,
        @Nonnull String value
    ) {
        this.minLength = ofNullable(minLength).map(v -> requireNonNegative(v, "minLength"));
        this.maxLength = ofNullable(maxLength).map(v -> requireNonNegative(v, "maxLength"));
        this.value = requireNonNull(value, "value");

        this.minLength.ifPresent(v -> {
            if (this.value.length() < v) {
                throw new IllegalArgumentException(format("'%s' is expected to be at least %d long; but is %d.", this.value, v, this.value.length()));
            }
        });
        this.maxLength.ifPresent(v -> {
            if (this.value.length() > v) {
                throw new IllegalArgumentException(format("'%s' is expected to be not longer than %d; but is %d.", this.value, v, this.value.length()));
            }
        });
    }

    @Override
    public final int compareTo(@Nonnull T o) {
        return value().compareTo(o.value());
    }

    @JsonValue
    @Nonnull
    public final String value() {
        return value;
    }

    @Nonnull
    @Nonnegative
    protected final Optional<Integer> minLength() {
        return minLength;
    }

    @Nonnull
    @Nonnegative
    protected final Optional<Integer> maxLength() {
        return maxLength;
    }

    @Override
    public final String toString() {
        return value();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || !getClass().equals(o.getClass())) {return false;}
        final var that = (StringKind<?>) o;
        return Objects.equals(minLength, that.minLength)
            && Objects.equals(maxLength, that.maxLength)
            && Objects.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(minLength, maxLength, value);
    }

}
