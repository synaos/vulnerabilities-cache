package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Optional;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CnaContainer {
    @Nonnull
    @JsonProperty("providerMetadata")
    private final ProviderMetadata providerMetadata;
    @Nonnull
    @JsonProperty("dateAssigned")
    private final Optional<ZonedDateTime> dateAssigned;
    @Nonnull
    @JsonProperty("datePublic")
    private final Optional<ZonedDateTime> datePublic;
    @Nonnull
    @JsonProperty("title")
    private final Optional<Title> title;
    @Nonnull
    @JsonProperty("descriptions")
    private final Descriptions descriptions;
    @Nonnull
    @JsonProperty("affected")
    private final Products affected;
    @Nonnull
    @JsonProperty("problemTypes")
    private final Optional<ProblemTypes> problemTypes;
    @Nonnull
    @JsonProperty("references")
    private final References references;
    @Nonnull
    @JsonProperty("impacts")
    private final Optional<Impacts> impacts;
    @Nonnull
    @JsonProperty("metrics")
    private final Optional<Metrics> metrics;




}
