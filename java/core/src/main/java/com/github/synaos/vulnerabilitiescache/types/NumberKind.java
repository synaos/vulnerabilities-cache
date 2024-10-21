package com.github.synaos.vulnerabilitiescache.types;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNegative;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import java.util.Comparator;
import java.util.Optional;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.github.synaos.vulnerabilitiescache.common.Objects;

public abstract class NumberKind<V extends Number, T extends NumberKind<V, T>> implements Comparable<T> {

    @Nonnull
    private final Comparator<V> comparator;
    @Nonnegative
    private final Optional<V> min;
    @Nonnegative
    private final Optional<V> max;
    @Nonnull
    private final V value;

    protected NumberKind(
        @Nonnull Comparator<V> comparator,
        @Nullable @Nonnegative V min,
        @Nullable @Nonnegative V max,
        @Nonnull V value
    ) {
        this.comparator = requireNonNull(comparator, "comparator");
        this.min = ofNullable(min).map(v -> requireNonNegative(v, "min"));
        this.max = ofNullable(max).map(v -> requireNonNegative(v, "max"));
        this.value = requireNonNull(value, "value");

        this.min.ifPresent(v -> {
            if (comparator.compare(this.value, v) < 0) {
                throw new IllegalArgumentException(format("'%s' is expected to be at least %s.", this.value, v));
            }
        });
        this.max.ifPresent(v -> {
            if (comparator.compare(this.value, v) > 0) {
                throw new IllegalArgumentException(format("'%s' is expected to be not lager %s.", this.value, v));
            }
        });
    }

    @Override
    public int compareTo(@Nonnull T o) {
        return comparator.compare(value, o.value());
    }

    @JsonValue
    @Nonnull
    public final V value() {
        return value;
    }

    @Nonnull
    protected final Comparator<V> comparator() {
        return comparator;
    }

    @Nonnull
    @Nonnegative
    protected final Optional<V> min() {
        return min;
    }

    @Nonnull
    @Nonnegative
    protected final Optional<V> max() {
        return max;
    }

    @Override
    public final String toString() {
        return value().toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || !getClass().equals(o.getClass())) {return false;}
        final var that = (NumberKind<?, ?>) o;
        return Objects.equals(min, that.min)
            && Objects.equals(max, that.max)
            && Objects.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(min, max, value);
    }

}
