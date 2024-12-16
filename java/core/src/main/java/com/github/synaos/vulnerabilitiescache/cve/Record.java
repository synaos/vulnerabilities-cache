package com.github.synaos.vulnerabilitiescache.cve;


import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

import java.util.Locale;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.Id;
import org.jetbrains.annotations.NotNull;

@JsonDeserialize(builder = Record.Builder.class)
@ThreadSafe
@Immutable
public final class Record implements Comparable<Record> {

    @Override
    public int compareTo(@NotNull Record o) {
        return cveMetadata.compareTo(o.cveMetadata);
    }

    @Override
    public String toString() {
        return cveMetadata().toString();
    }

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

    private Record(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.dataType = requireToBePresent(builder.dataType, "dataType");
        this.dataVersion = requireToBePresent(builder.dataVersion, "dataVersion");
        this.cveMetadata = requireToBePresent(builder.cveMetadata, "cveMetadata");
        this.containers = requireToBePresent(builder.containers, "containers");
        this.language = builder.language;
        this.englishLanguage = builder.englishLanguage;
        this.taxonomyMappings = builder.taxonomyMappings;
        this.tagExtension = builder.tagExtension;
    }

    @Nonnull
    public static Builder newRecord() {
        return new Builder();
    }

    @Nonnull
    @JsonIgnore
    public Id.Cve id() {
        return cveMetadata().cveId();
    }

    @JsonProperty(value = "dataType", required = true)
    @Nonnull
    public DataType dataType() {
        return dataType;
    }

    @JsonProperty(value = "dataVersion", required = true)
    @Nonnull
    public DataVersion dataVersion() {
        return dataVersion;
    }

    @JsonProperty(value = "cveMetadata", required = true)
    @Nonnull
    public CveMetadata cveMetadata() {
        return cveMetadata;
    }

    @JsonProperty(value = "containers", required = true)
    @Nonnull
    public RecordContainers containers() {
        return containers;
    }

    @JsonProperty("language")
    @Nonnull
    public Optional<Locale> language() {
        return language;
    }

    @JsonProperty("englishLanguage")
    @Nonnull
    public Optional<Locale> englishLanguage() {
        return englishLanguage;
    }

    @JsonProperty("taxonomyMappings")
    @Nonnull
    public Optional<TaxonomyMappings> taxonomyMappings() {
        return taxonomyMappings;
    }

    @JsonProperty("tagExtension")
    @Nonnull
    public Optional<TagExtension> tagExtension() {
        return tagExtension;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<DataType> dataType = Optional.empty();
        @Nonnull
        private Optional<DataVersion> dataVersion = Optional.empty();
        @Nonnull
        private Optional<CveMetadata> cveMetadata = Optional.empty();
        @Nonnull
        private Optional<RecordContainers> containers = Optional.empty();
        @Nonnull
        private Optional<Locale> language = Optional.empty();
        @Nonnull
        private Optional<Locale> englishLanguage = Optional.empty();
        @Nonnull
        private Optional<TaxonomyMappings> taxonomyMappings = Optional.empty();
        @Nonnull
        private Optional<TagExtension> tagExtension = Optional.empty();

        @JsonProperty(value = "dataType", required = true)
        @Nonnull
        public Builder withDataType(@Nonnull DataType v) {
            this.dataType = ofNonNull(v, "dataType");
            return this;
        }

        @JsonProperty(value = "dataVersion", required = true)
        @Nonnull
        public Builder withDataVersion(@Nonnull DataVersion v) {
            this.dataVersion = ofNonNull(v, "dataVersion");
            return this;
        }

        @JsonProperty(value = "cveMetadata", required = true)
        @Nonnull
        public Builder withCveMetadata(@Nonnull CveMetadata v) {
            this.cveMetadata = ofNonNull(v, "cveMetadata");
            return this;
        }

        @JsonProperty(value = "containers", required = true)
        @Nonnull
        public Builder withContainers(@Nonnull RecordContainers v) {
            this.containers = ofNonNull(v, "containers");
            return this;
        }

        @JsonProperty("language")
        @Nonnull
        public Builder withLanguage(@Nullable Locale v) {
            this.language = ofNullable(v);
            return this;
        }

        @JsonProperty("englishLanguage")
        @Nonnull
        public Builder withEnglishLanguage(@Nullable Locale v) {
            this.englishLanguage = ofNullable(v);
            return this;
        }

        @JsonProperty("taxonomyMappings")
        @Nonnull
        public Builder withTaxonomyMappings(@Nullable TaxonomyMappings v) {
            this.taxonomyMappings = ofNullable(v);
            return this;
        }

        @JsonProperty("tagExtension")
        @Nonnull
        public Builder withTagExtension(@Nullable TagExtension v) {
            this.tagExtension = ofNullable(v);
            return this;
        }

        @Nonnull
        public Record build() {
            return new Record(this);
        }
    }
}
