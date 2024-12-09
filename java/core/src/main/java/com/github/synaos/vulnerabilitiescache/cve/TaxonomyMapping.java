package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class TaxonomyMapping {
    @Nonnull
    @JsonProperty("taxonomyName")
    private final TaxonomyName taxonomyName;
    @Nonnull
    @JsonProperty("taxonomyVersion")
    private final Optional<TaxonomyVersion> taxonomyVersion;
    @Nonnull
    @JsonProperty("taxonomyRelations")
    private final TaxonomyRelations taxonomyRelations;
}
