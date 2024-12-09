package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


@ThreadSafe
@Immutable
public final class ProductName extends StringKind<ProductName> {

    @JsonCreator
    @Nonnull
    public static ProductName programName(@Nonnull String v) {
        return new ProductName(v);
    }

    private ProductName(@Nonnull String value) {
        super(1, 2048, value);
    }

}
