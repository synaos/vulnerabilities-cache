package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
@JsonIgnoreProperties({
    "source"
})
public final class AdpContainer {
    @Nonnull
    @JsonProperty("providerMetadata")
    private final ProviderMetadata providerMetadata;
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
    @Nonnull
    @JsonProperty("configurations")
    private final Optional<Configurations> configurations;
    @Nonnull
    @JsonProperty("workarounds")
    private final Optional<Workarounds> workarounds;
    @Nonnull
    @JsonProperty("solutions")
    private final Optional<Solutions> solutions;
    @Nonnull
    @JsonProperty("exploits")
    private final Optional<Exploits> exploits;
    @Nonnull
    @JsonProperty("timeline")
    private final Optional<Timeline> timeline;
    @Nonnull
    @JsonProperty("credits")
    private final Optional<Credits> credits;
    @Nonnull
    @JsonProperty("tags")
    private final Optional<AdpTags> tags;
    @Nonnull
    @JsonProperty("taxonomyMappings")
    private final Optional<TaxonomyMappings> taxonomyMappings;


}
