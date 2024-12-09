package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class ProviderMetadata {

    @Nonnull
    @JsonProperty("orgId")
    private final UUID orgId;
    @Nonnull
    @JsonProperty("shortName")
    private final Optional<String> shortName;
    @Nonnull
    @JsonProperty("dateUpdated")
    private final Optional<ZonedDateTime> dateUpdated;


}
