package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class RecordContainers {

    @Nonnull
    @JsonProperty(value = "cna")
    private final CnaContainer cna;
    @Nonnull
    @JsonProperty(value = "adp")
    private final Optional<AdpContainers> adp;

}
