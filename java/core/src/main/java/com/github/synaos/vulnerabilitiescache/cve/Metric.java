package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Optional;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Metric {
    @Nonnull
    @JsonProperty("format")
    private final Optional<MetricFormat> format;
    @Nonnull
    @JsonProperty("format")
    private final Optional<MetricScenarios> scenarios;

}
