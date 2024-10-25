package com.github.synaos.vulnerabilitiescache.cve;


import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class Record {

    @Nonnull
    private final DataType dataType;
    @Nonnull
    private final String dataVersion;
    @Nonnull
    private final CveMetadata cveMetadata;
    @Nonnull
    private final RecordContainers containers;

    @Nonnull
    @JsonProperty(value = "dataType", required = true)
    public DataType dataType() {
        return dataType;
    }

    @Nonnull
    @JsonProperty(value = "dataVersion", required = true)
    public String dataVersion() {
        return dataVersion;
    }

    @Nonnull
    @JsonProperty(value = "cveMetadata", required = true)
    public CveMetadata cveMetadata() {
        return cveMetadata;
    }

    @Nonnull
    @JsonProperty(value = "containers", required = true)
    public RecordContainers containers() {
        return containers;
    }
}
