package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.synaos.vulnerabilitiescache.Id;

@ThreadSafe
@Immutable
@JsonIgnoreProperties({
    "source"
})
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
    private final Optional<Descriptions> descriptions;
    @Nonnull
    @JsonProperty("rejectedReasons")
    private final Optional<Descriptions> rejectedReasons;
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
    private final Optional<CnaTags> tags;
    @Nonnull
    @JsonProperty("taxonomyMappings")
    private final Optional<TaxonomyMappings> taxonomyMappings;
    @Nonnull
    @JsonProperty("replacedBy")
    private final Optional<Id.Cves> replacedBy;


}
