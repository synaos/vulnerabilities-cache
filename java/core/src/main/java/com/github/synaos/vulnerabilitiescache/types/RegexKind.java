package com.github.synaos.vulnerabilitiescache.types;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.lang.String.format;

import java.util.regex.Pattern;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.github.synaos.vulnerabilitiescache.common.Objects;

@ThreadSafe
@Immutable
public abstract class RegexKind<T extends RegexKind<T>> extends StringKind<T> {

    @Nonnull
    private final Pattern pattern;

    protected RegexKind(
        @Nonnull Pattern pattern,
        @Nonnull String value
    ) {
        this(
            null,
            null,
            requireNonNull(pattern, "pattern"),
            requireNonNull(value, "value")
        );
    }

    protected RegexKind(
        @Nullable @Nonnegative Integer minLength,
        @Nullable @Nonnegative Integer maxLength,
        @Nonnull Pattern pattern,
        @Nonnull String value
    ) {
        super(
            minLength,
            maxLength,
            requireNonNull(value, "value")
        );
        this.pattern = requireNonNull(pattern, "pattern");

        if (!this.pattern.matcher(value).find()) {
            throw new IllegalArgumentException(format("'%s' does not match %s.", this.value(), this.pattern));
        }
    }

    @Nonnull
    protected final Pattern pattern() {
        return pattern;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || !getClass().equals(o.getClass())) {return false;}
        final var that = (RegexKind<?>) o;
        return super.equals(o) && Objects.equals(pattern, that.pattern);
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ pattern.hashCode();
    }
}
