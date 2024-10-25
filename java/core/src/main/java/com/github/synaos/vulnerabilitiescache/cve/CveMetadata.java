package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.Id;
import com.github.synaos.vulnerabilitiescache.Id.Cve;

@JsonDeserialize(builder = Address.Builder.class)
public final class CveMetadata {

    @Nonnull
    private final Id.Cve cveId;
    @Nonnull
    private final UUID assignerOrgId;
    @Nonnull
    private final Optional<ShortName> assignerShortName;
    @Nonnull
    private final Optional<UUID> requesterUserId;
    @Nonnull
    private final Optional<ZonedDateTime> dateUpdated;
    @Nonnull
    private final Optional<Integer> serial;
    @Nonnull
    private final Optional<ZonedDateTime> dateReserved;
    @Nonnull
    private final Optional<ZonedDateTime> datePublished;
    @Nonnull
    private final Optional<ZonedDateTime> dateRejected;
    @Nonnull
    private final State state;

    @Nonnull
    @JsonProperty(value = "cveId")
    public Cve cveId() {
        return cveId;
    }

    @Nonnull
    @JsonProperty(value = "assignerOrgId")
    public UUID assignerOrgId() {
        return assignerOrgId;
    }

    @Nonnull
    @JsonProperty(value = "assignerShortName")
    public Optional<ShortName> assignerShortName() {
        return assignerShortName;
    }

    @Nonnull
    @JsonProperty(value = "requesterUserId")
    public Optional<UUID> requesterUserId() {
        return requesterUserId;
    }

    @Nonnull
    @JsonProperty(value = "dateUpdated")
    public Optional<ZonedDateTime> dateUpdated() {
        return dateUpdated;
    }

    @Nonnull
    @JsonProperty(value = "serial")
    public Optional<Integer> serial() {
        return serial;
    }

    @Nonnull
    @JsonProperty(value = "dateReserved")
    public Optional<ZonedDateTime> dateReserved() {
        return dateReserved;
    }

    @Nonnull
    @JsonProperty(value = "datePublished")
    public Optional<ZonedDateTime> datePublished() {
        return datePublished;
    }

    @Nonnull
    @JsonProperty(value = "dateRejected")
    public Optional<ZonedDateTime> dateRejected() {
        return dateRejected;
    }

    @Nonnull
    @JsonProperty(value = "state", required = true)
    public State state() {
        return state;
    }

    @JsonPOJOBuilder()
    public static final class Builder {}
}
