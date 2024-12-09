package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.synaos.vulnerabilitiescache.Id.Capec;

@ThreadSafe
@Immutable
public final class Impact {
    @Nonnull
    @JsonProperty("capecId")
    private final Optional<Capec> capecId;
    @Nonnull
    @JsonProperty("descriptions")
    private final Descriptions descriptions;
}
