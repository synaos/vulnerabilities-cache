package com.github.synaos.vulnerabilitiescache.types;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.lang.String.format;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonValue;
import com.github.synaos.vulnerabilitiescache.common.Objects;

public abstract class RegexKind<T extends RegexKind<T>> implements Comparable<T> {

    @Nonnull
    private final Pattern pattern;
    @Nonnull
    private final String value;

    protected RegexKind(
        @Nonnull Pattern pattern,
        @Nonnull String value
    ) {
        this.pattern = requireNonNull(pattern, "pattern");
        this.value = requireNonNull(value, "value");

        if (!this.pattern.matcher(value).matches()) {
            throw new IllegalArgumentException(format("'%s' does not match %s.", this.value, this.pattern));
        }
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
    protected final Pattern pattern() {
        return pattern;
    }

    @Override
    public final String toString() {
        return value();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || !getClass().equals(o.getClass())) {return false;}
        final var that = (RegexKind<?>) o;
        return Objects.equals(pattern, that.pattern)
            && Objects.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(pattern, value);
    }

}
