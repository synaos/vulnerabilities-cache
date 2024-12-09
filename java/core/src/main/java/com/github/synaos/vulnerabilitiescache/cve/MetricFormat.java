package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


@ThreadSafe
@Immutable
public final class MetricFormat extends StringKind<MetricFormat> {

    @JsonCreator
    @Nonnull
    public static MetricFormat metricFormat(@Nonnull String v) {
        return new MetricFormat(v);
    }

    private MetricFormat(@Nonnull String value) {
        super(1, 64, value);
    }

}
