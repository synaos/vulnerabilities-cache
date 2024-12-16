package com.github.synaos.vulnerabilitiescache.cvss.v4;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = Cvss.Builder.class)
@ThreadSafe
@Immutable
public final class Cvss {
    @Nonnull
    @JsonProperty("version")
    private final Version version;
    @Nonnull
    @JsonProperty("vectorString")
    private final VectorString vectorString;
    @JsonProperty("baseScore")
    private final Score baseScore;
    @Nonnull
    @JsonProperty("baseSeverity")
    private final Severity baseSeverity;
    @Nonnull
    @JsonProperty("attackVector")
    private final Optional<AttackVector> attackVector;
    @Nonnull
    @JsonProperty("attackComplexity")
    private final Optional<AttackComplexity> attackComplexity;
    @Nonnull
    @JsonProperty("attackRequirements")
    private final Optional<AttackRequirements> attackRequirements;
    @Nonnull
    @JsonProperty("privilegesRequired")
    private final Optional<PrivilegesRequired> privilegesRequired;
    @Nonnull
    @JsonProperty("userInteraction")
    private final Optional<UserInteraction> userInteraction;
    @Nonnull
    @JsonProperty("vulnConfidentialityImpact")
    private final Optional<VulnCia> vulnConfidentialityImpact;
    @Nonnull
    @JsonProperty("vulnIntegrityImpact")
    private final Optional<VulnCia> vulnIntegrityImpact;
    @Nonnull
    @JsonProperty("vulnAvailabilityImpact")
    private final Optional<VulnCia> vulnAvailabilityImpact;
    @Nonnull
    @JsonProperty("subConfidentialityImpact")
    private final Optional<SubCia> subConfidentialityImpact;
    @Nonnull
    @JsonProperty("subIntegrityImpact")
    private final Optional<SubCia> subIntegrityImpact;
    @Nonnull
    @JsonProperty("subAvailabilityImpact")
    private final Optional<SubCia> subAvailabilityImpact;
    @Nonnull
    @JsonProperty("exploitMaturity")
    private final Optional<ExploitMaturity> exploitMaturity;
    @Nonnull
    @JsonProperty("confidentialityRequirement")
    private final Optional<CiaRequirement> confidentialityRequirement;
    @Nonnull
    @JsonProperty("integrityRequirement")
    private final Optional<CiaRequirement> integrityRequirement;
    @Nonnull
    @JsonProperty("availabilityRequirement")
    private final Optional<CiaRequirement> availabilityRequirement;
    @Nonnull
    @JsonProperty("modifiedAttackVector")
    private final Optional<ModifiedAttackVector> modifiedAttackVector;
    @Nonnull
    @JsonProperty("modifiedAttackComplexity")
    private final Optional<ModifiedAttackComplexity> modifiedAttackComplexity;
    @Nonnull
    @JsonProperty("modifiedAttackRequirements")
    private final Optional<ModifiedAttackRequirements> modifiedAttackRequirements;
    @Nonnull
    @JsonProperty("modifiedPrivilegesRequired")
    private final Optional<ModifiedPrivilegesRequired> modifiedPrivilegesRequired;
    @Nonnull
    @JsonProperty("modifiedUserInteraction")
    private final Optional<ModifiedUserInteraction> modifiedUserInteraction;
    @Nonnull
    @JsonProperty("modifiedVulnConfidentialityImpact")
    private final Optional<ModifiedVulnCia> modifiedVulnConfidentialityImpact;
    @Nonnull
    @JsonProperty("modifiedVulnIntegrityImpact")
    private final Optional<ModifiedVulnCia> modifiedVulnIntegrityImpact;
    @Nonnull
    @JsonProperty("modifiedVulnAvailabilityImpact")
    private final Optional<ModifiedVulnCia> modifiedVulnAvailabilityImpact;
    @Nonnull
    @JsonProperty("modifiedSubConfidentialityImpact")
    private final Optional<ModifiedSubIa> modifiedSubConfidentialityImpact;
    @Nonnull
    @JsonProperty("modifiedSubAvailabilityImpact")
    private final Optional<ModifiedSubIa> modifiedSubAvailabilityImpact;
    @Nonnull
    @JsonProperty("Safety")
    private final Optional<Safety> safety;
    @Nonnull
    @JsonProperty("Automatable")
    private final Optional<Automatable> automatable;
    @Nonnull
    @JsonProperty("Recovery")
    private final Optional<Recovery> recovery;
    @Nonnull
    @JsonProperty("valueDensity")
    private final Optional<ValueDensity> valueDensity;
    @Nonnull
    @JsonProperty("vulnerabilityResponseEffort")
    private final Optional<VulnerabilityResponseEffort> vulnerabilityResponseEffort;
    @Nonnull
    @JsonProperty("providerUrgency")
    private final Optional<ProviderUrgency> providerUrgency;

    private Cvss(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.version = requireToBePresent(builder.version, "version");
        this.vectorString = requireToBePresent(builder.vectorString, "vectorString");
        this.baseScore = requireToBePresent(builder.baseScore, "baseScore");
        this.baseSeverity = requireToBePresent(builder.baseSeverity, "baseSeverity");
        this.attackVector = builder.attackVector;
        this.attackComplexity = builder.attackComplexity;
        this.attackRequirements = builder.attackRequirements;
        this.privilegesRequired = builder.privilegesRequired;
        this.userInteraction = builder.userInteraction;
        this.vulnConfidentialityImpact = builder.vulnConfidentialityImpact;
        this.vulnIntegrityImpact = builder.vulnIntegrityImpact;
        this.vulnAvailabilityImpact = builder.vulnAvailabilityImpact;
        this.subConfidentialityImpact = builder.subConfidentialityImpact;
        this.subIntegrityImpact = builder.subIntegrityImpact;
        this.subAvailabilityImpact = builder.subAvailabilityImpact;
        this.exploitMaturity = builder.exploitMaturity;
        this.confidentialityRequirement = builder.confidentialityRequirement;
        this.integrityRequirement = builder.integrityRequirement;
        this.availabilityRequirement = builder.availabilityRequirement;
        this.modifiedAttackVector = builder.modifiedAttackVector;
        this.modifiedAttackComplexity = builder.modifiedAttackComplexity;
        this.modifiedAttackRequirements = builder.modifiedAttackRequirements;
        this.modifiedPrivilegesRequired = builder.modifiedPrivilegesRequired;
        this.modifiedUserInteraction = builder.modifiedUserInteraction;
        this.modifiedVulnConfidentialityImpact = builder.modifiedVulnConfidentialityImpact;
        this.modifiedVulnIntegrityImpact = builder.modifiedVulnIntegrityImpact;
        this.modifiedVulnAvailabilityImpact = builder.modifiedVulnAvailabilityImpact;
        this.modifiedSubConfidentialityImpact = builder.modifiedSubConfidentialityImpact;
        this.modifiedSubAvailabilityImpact = builder.modifiedSubAvailabilityImpact;
        this.safety = builder.safety;
        this.automatable = builder.automatable;
        this.recovery = builder.recovery;
        this.valueDensity = builder.valueDensity;
        this.vulnerabilityResponseEffort = builder.vulnerabilityResponseEffort;
        this.providerUrgency = builder.providerUrgency;
    }

    @Nonnull
    public static Builder newCvss() {
        return new Builder();
    }

    @Nonnull
    public static Builder newCvss4() {
        return newCvss();
    }

    @JsonProperty(value = "version", required = true)
    @Nonnull
    public Version version() {
        return version;
    }

    @JsonProperty(value = "vectorString", required = true)
    @Nonnull
    public VectorString vectorString() {
        return vectorString;
    }

    @JsonProperty(value = "baseScore", required = true)
    @Nonnull
    public Score baseScore() {
        return baseScore;
    }

    @JsonProperty(value = "baseSeverity", required = true)
    @Nonnull
    public Severity baseSeverity() {
        return baseSeverity;
    }

    @JsonProperty("attackVector")
    @Nonnull
    public Optional<AttackVector> attackVector() {
        return attackVector;
    }

    @JsonProperty("attackComplexity")
    @Nonnull
    public Optional<AttackComplexity> attackComplexity() {
        return attackComplexity;
    }

    @JsonProperty("attackRequirements")
    @Nonnull
    public Optional<AttackRequirements> attackRequirements() {
        return attackRequirements;
    }

    @JsonProperty("privilegesRequired")
    @Nonnull
    public Optional<PrivilegesRequired> privilegesRequired() {
        return privilegesRequired;
    }

    @JsonProperty("userInteraction")
    @Nonnull
    public Optional<UserInteraction> userInteraction() {
        return userInteraction;
    }

    @JsonProperty("vulnConfidentialityImpact")
    @Nonnull
    public Optional<VulnCia> vulnConfidentialityImpact() {
        return vulnConfidentialityImpact;
    }

    @JsonProperty("vulnIntegrityImpact")
    @Nonnull
    public Optional<VulnCia> vulnIntegrityImpact() {
        return vulnIntegrityImpact;
    }

    @JsonProperty("vulnAvailabilityImpact")
    @Nonnull
    public Optional<VulnCia> vulnAvailabilityImpact() {
        return vulnAvailabilityImpact;
    }

    @JsonProperty("subConfidentialityImpact")
    @Nonnull
    public Optional<SubCia> subConfidentialityImpact() {
        return subConfidentialityImpact;
    }

    @JsonProperty("subIntegrityImpact")
    @Nonnull
    public Optional<SubCia> subIntegrityImpact() {
        return subIntegrityImpact;
    }

    @JsonProperty("subAvailabilityImpact")
    @Nonnull
    public Optional<SubCia> subAvailabilityImpact() {
        return subAvailabilityImpact;
    }

    @JsonProperty("exploitMaturity")
    @Nonnull
    public Optional<ExploitMaturity> exploitMaturity() {
        return exploitMaturity;
    }

    @JsonProperty("confidentialityRequirement")
    @Nonnull
    public Optional<CiaRequirement> confidentialityRequirement() {
        return confidentialityRequirement;
    }

    @JsonProperty("integrityRequirement")
    @Nonnull
    public Optional<CiaRequirement> integrityRequirement() {
        return integrityRequirement;
    }

    @JsonProperty("availabilityRequirement")
    @Nonnull
    public Optional<CiaRequirement> availabilityRequirement() {
        return availabilityRequirement;
    }

    @JsonProperty("modifiedAttackVector")
    @Nonnull
    public Optional<ModifiedAttackVector> modifiedAttackVector() {
        return modifiedAttackVector;
    }

    @JsonProperty("modifiedAttackComplexity")
    @Nonnull
    public Optional<ModifiedAttackComplexity> modifiedAttackComplexity() {
        return modifiedAttackComplexity;
    }

    @JsonProperty("modifiedAttackRequirements")
    @Nonnull
    public Optional<ModifiedAttackRequirements> modifiedAttackRequirements() {
        return modifiedAttackRequirements;
    }

    @JsonProperty("modifiedPrivilegesRequired")
    @Nonnull
    public Optional<ModifiedPrivilegesRequired> modifiedPrivilegesRequired() {
        return modifiedPrivilegesRequired;
    }

    @JsonProperty("modifiedUserInteraction")
    @Nonnull
    public Optional<ModifiedUserInteraction> modifiedUserInteraction() {
        return modifiedUserInteraction;
    }

    @JsonProperty("modifiedVulnConfidentialityImpact")
    @Nonnull
    public Optional<ModifiedVulnCia> modifiedVulnConfidentialityImpact() {
        return modifiedVulnConfidentialityImpact;
    }

    @JsonProperty("modifiedVulnIntegrityImpact")
    @Nonnull
    public Optional<ModifiedVulnCia> modifiedVulnIntegrityImpact() {
        return modifiedVulnIntegrityImpact;
    }

    @JsonProperty("modifiedVulnAvailabilityImpact")
    @Nonnull
    public Optional<ModifiedVulnCia> modifiedVulnAvailabilityImpact() {
        return modifiedVulnAvailabilityImpact;
    }

    @JsonProperty("modifiedSubConfidentialityImpact")
    @Nonnull
    public Optional<ModifiedSubIa> modifiedSubConfidentialityImpact() {
        return modifiedSubConfidentialityImpact;
    }

    @JsonProperty("modifiedSubAvailabilityImpact")
    @Nonnull
    public Optional<ModifiedSubIa> modifiedSubAvailabilityImpact() {
        return modifiedSubAvailabilityImpact;
    }

    @JsonProperty("Safety")
    @Nonnull
    public Optional<Safety> safety() {
        return safety;
    }

    @JsonProperty("Automatable")
    @Nonnull
    public Optional<Automatable> automatable() {
        return automatable;
    }

    @JsonProperty("Recovery")
    @Nonnull
    public Optional<Recovery> recovery() {
        return recovery;
    }

    @JsonProperty("valueDensity")
    @Nonnull
    public Optional<ValueDensity> valueDensity() {
        return valueDensity;
    }

    @JsonProperty("vulnerabilityResponseEffort")
    @Nonnull
    public Optional<VulnerabilityResponseEffort> vulnerabilityResponseEffort() {
        return vulnerabilityResponseEffort;
    }

    @JsonProperty("providerUrgency")
    @Nonnull
    public Optional<ProviderUrgency> providerUrgency() {
        return providerUrgency;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Version> version = Optional.empty();
        @Nonnull
        private Optional<VectorString> vectorString = Optional.empty();
        private Optional<Score> baseScore = Optional.empty();
        @Nonnull
        private Optional<Severity> baseSeverity = Optional.empty();
        @Nonnull
        private Optional<AttackVector> attackVector = Optional.empty();
        @Nonnull
        private Optional<AttackComplexity> attackComplexity = Optional.empty();
        @Nonnull
        private Optional<AttackRequirements> attackRequirements = Optional.empty();
        @Nonnull
        private Optional<PrivilegesRequired> privilegesRequired = Optional.empty();
        @Nonnull
        private Optional<UserInteraction> userInteraction = Optional.empty();
        @Nonnull
        private Optional<VulnCia> vulnConfidentialityImpact = Optional.empty();
        @Nonnull
        private Optional<VulnCia> vulnIntegrityImpact = Optional.empty();
        @Nonnull
        private Optional<VulnCia> vulnAvailabilityImpact = Optional.empty();
        @Nonnull
        private Optional<SubCia> subConfidentialityImpact = Optional.empty();
        @Nonnull
        private Optional<SubCia> subIntegrityImpact = Optional.empty();
        @Nonnull
        private Optional<SubCia> subAvailabilityImpact = Optional.empty();
        @Nonnull
        private Optional<ExploitMaturity> exploitMaturity = Optional.empty();
        @Nonnull
        private Optional<CiaRequirement> confidentialityRequirement = Optional.empty();
        @Nonnull
        private Optional<CiaRequirement> integrityRequirement = Optional.empty();
        @Nonnull
        private Optional<CiaRequirement> availabilityRequirement = Optional.empty();
        @Nonnull
        private Optional<ModifiedAttackVector> modifiedAttackVector = Optional.empty();
        @Nonnull
        private Optional<ModifiedAttackComplexity> modifiedAttackComplexity = Optional.empty();
        @Nonnull
        private Optional<ModifiedAttackRequirements> modifiedAttackRequirements = Optional.empty();
        @Nonnull
        private Optional<ModifiedPrivilegesRequired> modifiedPrivilegesRequired = Optional.empty();
        @Nonnull
        private Optional<ModifiedUserInteraction> modifiedUserInteraction = Optional.empty();
        @Nonnull
        private Optional<ModifiedVulnCia> modifiedVulnConfidentialityImpact = Optional.empty();
        @Nonnull
        private Optional<ModifiedVulnCia> modifiedVulnIntegrityImpact = Optional.empty();
        @Nonnull
        private Optional<ModifiedVulnCia> modifiedVulnAvailabilityImpact = Optional.empty();
        @Nonnull
        private Optional<ModifiedSubIa> modifiedSubConfidentialityImpact = Optional.empty();
        @Nonnull
        private Optional<ModifiedSubIa> modifiedSubAvailabilityImpact = Optional.empty();
        @Nonnull
        private Optional<Safety> safety = Optional.empty();
        @Nonnull
        private Optional<Automatable> automatable = Optional.empty();
        @Nonnull
        private Optional<Recovery> recovery = Optional.empty();
        @Nonnull
        private Optional<ValueDensity> valueDensity = Optional.empty();
        @Nonnull
        private Optional<VulnerabilityResponseEffort> vulnerabilityResponseEffort = Optional.empty();
        @Nonnull
        private Optional<ProviderUrgency> providerUrgency = Optional.empty();

        @JsonProperty(value = "version", required = true)
        @Nonnull
        public Builder withVersion(@Nonnull Version v) {
            this.version = ofNonNull(v, "version");
            return this;
        }

        @JsonProperty(value = "vectorString", required = true)
        @Nonnull
        public Builder withVectorString(@Nonnull VectorString v) {
            this.vectorString = ofNonNull(v, "vectorString");
            return this;
        }

        @JsonProperty(value = "baseScore", required = true)
        @Nonnull
        public Builder withBaseScore(@Nonnull Score v) {
            this.baseScore = ofNonNull(v, "baseScore");
            return this;
        }

        @JsonProperty(value = "baseSeverity", required = true)
        @Nonnull
        public Builder withBaseSeverity(@Nonnull Severity v) {
            this.baseSeverity = ofNonNull(v, "baseSeverity");
            return this;
        }

        @JsonProperty("attackVector")
        @Nonnull
        public Builder withAttackVector(@Nullable AttackVector v) {
            this.attackVector = ofNullable(v);
            return this;
        }

        @JsonProperty("attackComplexity")
        @Nonnull
        public Builder withAttackComplexity(@Nullable AttackComplexity v) {
            this.attackComplexity = ofNullable(v);
            return this;
        }

        @JsonProperty("attackRequirements")
        @Nonnull
        public Builder withAttackRequirements(@Nullable AttackRequirements v) {
            this.attackRequirements = ofNullable(v);
            return this;
        }

        @JsonProperty("privilegesRequired")
        @Nonnull
        public Builder withPrivilegesRequired(@Nullable PrivilegesRequired v) {
            this.privilegesRequired = ofNullable(v);
            return this;
        }

        @JsonProperty("userInteraction")
        @Nonnull
        public Builder withUserInteraction(@Nullable UserInteraction v) {
            this.userInteraction = ofNullable(v);
            return this;
        }

        @JsonProperty("vulnConfidentialityImpact")
        @Nonnull
        public Builder withVulnConfidentialityImpact(@Nullable VulnCia v) {
            this.vulnConfidentialityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("vulnIntegrityImpact")
        @Nonnull
        public Builder withVulnIntegrityImpact(@Nullable VulnCia v) {
            this.vulnIntegrityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("vulnAvailabilityImpact")
        @Nonnull
        public Builder withVulnAvailabilityImpact(@Nullable VulnCia v) {
            this.vulnAvailabilityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("subConfidentialityImpact")
        @Nonnull
        public Builder withSubConfidentialityImpact(@Nullable SubCia v) {
            this.subConfidentialityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("subIntegrityImpact")
        @Nonnull
        public Builder withSubIntegrityImpact(@Nullable SubCia v) {
            this.subIntegrityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("subAvailabilityImpact")
        @Nonnull
        public Builder withSubAvailabilityImpact(@Nullable SubCia v) {
            this.subAvailabilityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("exploitMaturity")
        @Nonnull
        public Builder withExploitMaturity(@Nullable ExploitMaturity v) {
            this.exploitMaturity = ofNullable(v);
            return this;
        }

        @JsonProperty("confidentialityRequirement")
        @Nonnull
        public Builder withConfidentialityRequirement(@Nullable CiaRequirement v) {
            this.confidentialityRequirement = ofNullable(v);
            return this;
        }

        @JsonProperty("integrityRequirement")
        @Nonnull
        public Builder withIntegrityRequirement(@Nullable CiaRequirement v) {
            this.integrityRequirement = ofNullable(v);
            return this;
        }

        @JsonProperty("availabilityRequirement")
        @Nonnull
        public Builder withAvailabilityRequirement(@Nullable CiaRequirement v) {
            this.availabilityRequirement = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedAttackVector")
        @Nonnull
        public Builder withModifiedAttackVector(@Nullable ModifiedAttackVector v) {
            this.modifiedAttackVector = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedAttackComplexity")
        @Nonnull
        public Builder withModifiedAttackComplexity(@Nullable ModifiedAttackComplexity v) {
            this.modifiedAttackComplexity = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedAttackRequirements")
        @Nonnull
        public Builder withModifiedAttackRequirements(@Nullable ModifiedAttackRequirements v) {
            this.modifiedAttackRequirements = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedPrivilegesRequired")
        @Nonnull
        public Builder withModifiedPrivilegesRequired(@Nullable ModifiedPrivilegesRequired v) {
            this.modifiedPrivilegesRequired = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedUserInteraction")
        @Nonnull
        public Builder withModifiedUserInteraction(@Nullable ModifiedUserInteraction v) {
            this.modifiedUserInteraction = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedVulnConfidentialityImpact")
        @Nonnull
        public Builder withModifiedVulnConfidentialityImpact(@Nullable ModifiedVulnCia v) {
            this.modifiedVulnConfidentialityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedVulnIntegrityImpact")
        @Nonnull
        public Builder withModifiedVulnIntegrityImpact(@Nullable ModifiedVulnCia v) {
            this.modifiedVulnIntegrityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedVulnAvailabilityImpact")
        @Nonnull
        public Builder withModifiedVulnAvailabilityImpact(@Nullable ModifiedVulnCia v) {
            this.modifiedVulnAvailabilityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedSubConfidentialityImpact")
        @Nonnull
        public Builder withModifiedSubConfidentialityImpact(@Nullable ModifiedSubIa v) {
            this.modifiedSubConfidentialityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedSubAvailabilityImpact")
        @Nonnull
        public Builder withModifiedSubAvailabilityImpact(@Nullable ModifiedSubIa v) {
            this.modifiedSubAvailabilityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("Safety")
        @Nonnull
        public Builder withSafety(@Nullable Safety v) {
            this.safety = ofNullable(v);
            return this;
        }

        @JsonProperty("Automatable")
        @Nonnull
        public Builder withAutomatable(@Nullable Automatable v) {
            this.automatable = ofNullable(v);
            return this;
        }

        @JsonProperty("Recovery")
        @Nonnull
        public Builder withRecovery(@Nullable Recovery v) {
            this.recovery = ofNullable(v);
            return this;
        }

        @JsonProperty("valueDensity")
        @Nonnull
        public Builder withValueDensity(@Nullable ValueDensity v) {
            this.valueDensity = ofNullable(v);
            return this;
        }

        @JsonProperty("vulnerabilityResponseEffort")
        @Nonnull
        public Builder withVulnerabilityResponseEffort(@Nullable VulnerabilityResponseEffort v) {
            this.vulnerabilityResponseEffort = ofNullable(v);
            return this;
        }

        @JsonProperty("providerUrgency")
        @Nonnull
        public Builder withProviderUrgency(@Nullable ProviderUrgency v) {
            this.providerUrgency = ofNullable(v);
            return this;
        }

        @Nonnull
        public Cvss build() {
            return new Cvss(this);
        }
    }
}
