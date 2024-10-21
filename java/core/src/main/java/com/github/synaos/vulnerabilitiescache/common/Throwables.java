package com.github.synaos.vulnerabilitiescache.common;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;

import javax.annotation.Nonnull;

public interface Throwables {

    @Nonnull
    static <T> T throwSneaky(@Nonnull Throwable e) {
        requireNonNull(e, "throwable");
        _sneaky(e);
        return null; // never gets here, needed for compiler tho
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void _sneaky(@Nonnull Throwable e) throws E {
        throw (E) e;
    }

}
