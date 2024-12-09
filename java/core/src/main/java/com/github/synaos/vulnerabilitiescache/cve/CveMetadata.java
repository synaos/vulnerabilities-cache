package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.synaos.vulnerabilitiescache.Id;

@ThreadSafe
@Immutable
public final class CveMetadata {

    @Nonnull
    @JsonProperty(value = "cveId")
    private final Id.Cve cveId;
    @Nonnull
    @JsonProperty(value = "assignerOrgId")
    private final UUID assignerOrgId;
    @Nonnull
    @JsonProperty(value = "assignerShortName")
    private final Optional<ShortName> assignerShortName;
    @Nonnull
    @JsonProperty(value = "requesterUserId")
    private final Optional<UUID> requesterUserId;
    @Nonnull
    @JsonProperty(value = "dateUpdated")
    private final Optional<ZonedDateTime> dateUpdated;
    @Nonnull
    @JsonProperty(value = "serial")
    private final Optional<Integer> serial;
    @Nonnull
    @JsonProperty(value = "dateReserved")
    private final Optional<ZonedDateTime> dateReserved;
    @Nonnull
    @JsonProperty(value = "datePublished")
    private final Optional<ZonedDateTime> datePublished;
    @Nonnull
    @JsonProperty(value = "dateRejected")
    private final Optional<ZonedDateTime> dateRejected;
    @Nonnull
    @JsonProperty(value = "state")
    private final State state;

}
