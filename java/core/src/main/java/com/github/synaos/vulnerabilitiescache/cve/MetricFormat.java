package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


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
