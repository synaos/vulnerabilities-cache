package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Locale;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MetricScenario {
    @Nonnull
    @JsonProperty("lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty("value")
    private final MetricScenarioValue value;
}
