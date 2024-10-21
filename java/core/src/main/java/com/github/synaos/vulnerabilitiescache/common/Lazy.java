package com.github.synaos.vulnerabilitiescache.common;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Lazy<I, T> {

    @Nonnull
    private final AtomicReference<T> value = new AtomicReference<>();
    @Nonnull
    private final Function<I, T> factory;

    public Lazy(@Nonnull Function<I, T> factory) {
        this.factory = requireNonNull(factory, "factory");
    }

    @Nonnull
    public T get(@Nullable I input) {
        while (true) {
            var result = value.get();
            if (result != null) {
                return result;
            }
            result = factory.apply(input);
            requireNonNull(result, "value");
            if (value.compareAndSet(null, result)) {
                return result;
            }
        }
    }

}
