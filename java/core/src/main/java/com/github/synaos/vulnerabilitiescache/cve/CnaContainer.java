package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

import java.time.ZonedDateTime;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.Id;

@JsonDeserialize(builder = CnaContainer.Builder.class)
@ThreadSafe
@Immutable
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
    private final Optional<Products> affected;
    @Nonnull
    @JsonProperty("cpeApplicability")
    private final Optional<CpeApplicabilities> cpeApplicability;
    @Nonnull
    @JsonProperty("problemTypes")
    private final Optional<ProblemTypes> problemTypes;
    @Nonnull
    @JsonProperty("references")
    private final Optional<References> references;
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

    private CnaContainer(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.providerMetadata = requireToBePresent(builder.providerMetadata, "providerMetadata");
        this.dateAssigned = builder.dateAssigned;
        this.datePublic = builder.datePublic;
        this.title = builder.title;
        this.descriptions = builder.descriptions;
        this.rejectedReasons = builder.rejectedReasons;
        this.affected = builder.affected;
        this.cpeApplicability = builder.cpeApplicability;
        this.problemTypes = builder.problemTypes;
        this.references = builder.references;
        this.impacts = builder.impacts;
        this.metrics = builder.metrics;
        this.configurations = builder.configurations;
        this.workarounds = builder.workarounds;
        this.solutions = builder.solutions;
        this.exploits = builder.exploits;
        this.timeline = builder.timeline;
        this.credits = builder.credits;
        this.tags = builder.tags;
        this.taxonomyMappings = builder.taxonomyMappings;
        this.replacedBy = builder.replacedBy;
    }

    @Nonnull
    public static Builder newCnaContainer() {
        return new Builder();
    }

    @JsonProperty(value = "providerMetadata", required = true)
    @Nonnull
    public ProviderMetadata providerMetadata() {
        return providerMetadata;
    }

    @JsonProperty("dateAssigned")
    @Nonnull
    public Optional<ZonedDateTime> dateAssigned() {
        return dateAssigned;
    }

    @JsonProperty("datePublic")
    @Nonnull
    public Optional<ZonedDateTime> datePublic() {
        return datePublic;
    }

    @JsonProperty("title")
    @Nonnull
    public Optional<Title> title() {
        return title;
    }

    @JsonProperty("descriptions")
    @Nonnull
    public Optional<Descriptions> descriptions() {
        return descriptions;
    }

    @JsonProperty("rejectedReasons")
    @Nonnull
    public Optional<Descriptions> rejectedReasons() {
        return rejectedReasons;
    }

    @JsonProperty(value = "affected")
    @Nonnull
    public Optional<Products> affected() {
        return affected;
    }

    @JsonProperty(value = "cpeApplicability")
    @Nonnull
    public Optional<CpeApplicabilities> cpeApplicability() {
        return cpeApplicability;
    }

    @JsonProperty("problemTypes")
    @Nonnull
    public Optional<ProblemTypes> problemTypes() {
        return problemTypes;
    }

    @JsonProperty(value = "references")
    @Nonnull
    public Optional<References> references() {
        return references;
    }

    @JsonProperty("impacts")
    @Nonnull
    public Optional<Impacts> impacts() {
        return impacts;
    }

    @JsonProperty("metrics")
    @Nonnull
    public Optional<Metrics> metrics() {
        return metrics;
    }

    @JsonProperty("configurations")
    @Nonnull
    public Optional<Configurations> configurations() {
        return configurations;
    }

    @JsonProperty("workarounds")
    @Nonnull
    public Optional<Workarounds> workarounds() {
        return workarounds;
    }

    @JsonProperty("solutions")
    @Nonnull
    public Optional<Solutions> solutions() {
        return solutions;
    }

    @JsonProperty("exploits")
    @Nonnull
    public Optional<Exploits> exploits() {
        return exploits;
    }

    @JsonProperty("timeline")
    @Nonnull
    public Optional<Timeline> timeline() {
        return timeline;
    }

    @JsonProperty("credits")
    @Nonnull
    public Optional<Credits> credits() {
        return credits;
    }

    @JsonProperty("tags")
    @Nonnull
    public Optional<CnaTags> tags() {
        return tags;
    }

    @JsonProperty("taxonomyMappings")
    @Nonnull
    public Optional<TaxonomyMappings> taxonomyMappings() {
        return taxonomyMappings;
    }

    @JsonProperty("replacedBy")
    @Nonnull
    public Optional<Id.Cves> replacedBy() {
        return replacedBy;
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties({"source"})
    public static final class Builder {
        @Nonnull
        private Optional<ProviderMetadata> providerMetadata = Optional.empty();
        @Nonnull
        private Optional<ZonedDateTime> dateAssigned = Optional.empty();
        @Nonnull
        private Optional<ZonedDateTime> datePublic = Optional.empty();
        @Nonnull
        private Optional<Title> title = Optional.empty();
        @Nonnull
        private Optional<Descriptions> descriptions = Optional.empty();
        @Nonnull
        private Optional<Descriptions> rejectedReasons = Optional.empty();
        @Nonnull
        private Optional<Products> affected = Optional.empty();
        @Nonnull
        private Optional<CpeApplicabilities> cpeApplicability = Optional.empty();
        @Nonnull
        private Optional<ProblemTypes> problemTypes = Optional.empty();
        @Nonnull
        private Optional<References> references = Optional.empty();
        @Nonnull
        private Optional<Impacts> impacts = Optional.empty();
        @Nonnull
        private Optional<Metrics> metrics = Optional.empty();
        @Nonnull
        private Optional<Configurations> configurations = Optional.empty();
        @Nonnull
        private Optional<Workarounds> workarounds = Optional.empty();
        @Nonnull
        private Optional<Solutions> solutions = Optional.empty();
        @Nonnull
        private Optional<Exploits> exploits = Optional.empty();
        @Nonnull
        private Optional<Timeline> timeline = Optional.empty();
        @Nonnull
        private Optional<Credits> credits = Optional.empty();
        @Nonnull
        private Optional<CnaTags> tags = Optional.empty();
        @Nonnull
        private Optional<TaxonomyMappings> taxonomyMappings = Optional.empty();
        @Nonnull
        private Optional<Id.Cves> replacedBy = Optional.empty();

        @JsonProperty(value = "providerMetadata", required = true)
        @Nonnull
        public Builder withProviderMetadata(@Nonnull ProviderMetadata v) {
            this.providerMetadata = ofNonNull(v, "providerMetadata");
            return this;
        }

        @JsonProperty("dateAssigned")
        @Nonnull
        public Builder withDateAssigned(@Nullable ZonedDateTime v) {
            this.dateAssigned = ofNullable(v);
            return this;
        }

        @JsonProperty("datePublic")
        @Nonnull
        public Builder withDatePublic(@Nullable ZonedDateTime v) {
            this.datePublic = ofNullable(v);
            return this;
        }

        @JsonProperty("title")
        @Nonnull
        public Builder withTitle(@Nullable Title v) {
            this.title = ofNullable(v);
            return this;
        }

        @JsonProperty("descriptions")
        @Nonnull
        public Builder withDescriptions(@Nullable Descriptions v) {
            this.descriptions = ofNullable(v);
            return this;
        }

        @JsonProperty("rejectedReasons")
        @Nonnull
        public Builder withRejectedReasons(@Nullable Descriptions v) {
            this.rejectedReasons = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "affected")
        @Nonnull
        public Builder withAffected(@Nullable Products v) {
            this.affected = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "cpeApplicability")
        @Nonnull
        public Builder withCpeApplicability(@Nullable CpeApplicabilities v) {
            this.cpeApplicability = ofNullable(v);
            return this;
        }

        @JsonProperty("problemTypes")
        @Nonnull
        public Builder withProblemTypes(@Nullable ProblemTypes v) {
            this.problemTypes = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "references")
        @Nonnull
        public Builder withReferences(@Nullable References v) {
            this.references = ofNullable(v);
            return this;
        }

        @JsonProperty("impacts")
        @Nonnull
        public Builder withImpacts(@Nullable Impacts v) {
            this.impacts = ofNullable(v);
            return this;
        }

        @JsonProperty("metrics")
        @Nonnull
        public Builder withMetrics(@Nullable Metrics v) {
            this.metrics = ofNullable(v);
            return this;
        }

        @JsonProperty("configurations")
        @Nonnull
        public Builder withConfigurations(@Nullable Configurations v) {
            this.configurations = ofNullable(v);
            return this;
        }

        @JsonProperty("workarounds")
        @Nonnull
        public Builder withWorkarounds(@Nullable Workarounds v) {
            this.workarounds = ofNullable(v);
            return this;
        }

        @JsonProperty("solutions")
        @Nonnull
        public Builder withSolutions(@Nullable Solutions v) {
            this.solutions = ofNullable(v);
            return this;
        }

        @JsonProperty("exploits")
        @Nonnull
        public Builder withExploits(@Nullable Exploits v) {
            this.exploits = ofNullable(v);
            return this;
        }

        @JsonProperty("timeline")
        @Nonnull
        public Builder withTimeline(@Nullable Timeline v) {
            this.timeline = ofNullable(v);
            return this;
        }

        @JsonProperty("credits")
        @Nonnull
        public Builder withCredits(@Nullable Credits v) {
            this.credits = ofNullable(v);
            return this;
        }

        @JsonProperty("tags")
        @Nonnull
        public Builder withTags(@Nullable CnaTags v) {
            this.tags = ofNullable(v);
            return this;
        }

        @JsonProperty("taxonomyMappings")
        @Nonnull
        public Builder withTaxonomyMappings(@Nullable TaxonomyMappings v) {
            this.taxonomyMappings = ofNullable(v);
            return this;
        }

        @JsonProperty("replacedBy")
        @Nonnull
        public Builder withReplacedBy(@Nullable Id.Cves v) {
            this.replacedBy = ofNullable(v);
            return this;
        }

        @Nonnull
        public CnaContainer build() {
            return new CnaContainer(this);
        }
    }
}
