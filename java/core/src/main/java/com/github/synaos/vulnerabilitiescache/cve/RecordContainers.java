package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RecordContainers {

    @Nonnull
    private final RecordCna cna;
    @Nonnull
    private final List<RecordAdp> adp;

    @Nonnull
    @JsonProperty(value = "cna", required = true)
    public RecordCna cna() {
        return cna;
    }

    @Nonnull
    @JsonProperty(value = "adp", required = true)
    public List<RecordAdp> adp() {
        return adp;
    }
}
