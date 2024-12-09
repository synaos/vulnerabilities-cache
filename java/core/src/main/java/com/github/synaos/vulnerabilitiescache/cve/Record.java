package com.github.synaos.vulnerabilitiescache.cve;


import java.util.Locale;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class Record {

    @Nonnull
    @JsonProperty("dataType")
    private final DataType dataType;
    @Nonnull
    @JsonProperty("dataVersion")
    private final DataVersion dataVersion;
    @Nonnull
    @JsonProperty("cveMetadata")
    private final CveMetadata cveMetadata;
    @Nonnull
    @JsonProperty("containers")
    private final RecordContainers containers;
    @Nonnull
    @JsonProperty("language")
    private final Optional<Locale> language;
    @Nonnull
    @JsonProperty("englishLanguage")
    private final Optional<Locale> englishLanguage;
    @Nonnull
    @JsonProperty("taxonomyMappings")
    private final Optional<TaxonomyMappings> taxonomyMappings;
    @Nonnull
    @JsonProperty("tagExtension")
    private final Optional<TagExtension> tagExtension;

}
