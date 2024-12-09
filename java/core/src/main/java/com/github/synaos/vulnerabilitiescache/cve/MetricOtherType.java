package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class MetricOtherType extends StringKind<MetricOtherType> {

    @JsonCreator
    @Nonnull
    public static MetricOtherType metricFormat(@Nonnull String v) {
        return new MetricOtherType(v);
    }

    private MetricOtherType(@Nonnull String value) {
        super(1, 128, value);
    }

}
