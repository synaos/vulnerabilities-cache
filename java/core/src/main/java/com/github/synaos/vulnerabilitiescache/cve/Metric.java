package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class Metric {
    @Nonnull
    @JsonProperty("format")
    private final Optional<MetricFormat> format;
    @Nonnull
    @JsonProperty("scenarios")
    private final Optional<MetricScenarios> scenarios;
    @Nonnull
    @JsonProperty("cvssV2_0")
    private final Optional<com.github.synaos.vulnerabilitiescache.cvss.v2.Cvss> cvssV2_0;
    @Nonnull
    @JsonProperty("cvssV3_0")
    private final Optional<com.github.synaos.vulnerabilitiescache.cvss.v3.Cvss> cvssV3_0;
    @Nonnull
    @JsonProperty("cvssV3_1")
    private final Optional<com.github.synaos.vulnerabilitiescache.cvss.v3d1.Cvss> cvssV3_1;
    @Nonnull
    @JsonProperty("cvssV4_0")
    private final Optional<com.github.synaos.vulnerabilitiescache.cvss.v4.Cvss> cvssV4_0;
    @Nonnull
    @JsonProperty("other")
    private final Optional<MetricOther> other;

}
