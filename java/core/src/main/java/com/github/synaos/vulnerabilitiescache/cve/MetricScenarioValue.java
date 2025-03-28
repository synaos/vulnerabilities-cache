package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


public final class MetricScenarioValue extends StringKind<MetricScenarioValue> {

    @JsonCreator
    @Nonnull
    public static MetricScenarioValue metricScenarioValue(@Nonnull String v) {
        return new MetricScenarioValue(v);
    }

    private MetricScenarioValue(@Nonnull String value) {
        super(1, 4096, value);
    }

}
