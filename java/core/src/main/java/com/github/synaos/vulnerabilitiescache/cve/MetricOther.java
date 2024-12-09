package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
@JsonIgnoreProperties({"content"})
public final class MetricOther {
    @Nonnull
    @JsonProperty("type")
    private final MetricOtherType type;

}
