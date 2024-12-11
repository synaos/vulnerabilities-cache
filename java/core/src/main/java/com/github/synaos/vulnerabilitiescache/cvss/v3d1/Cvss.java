package com.github.synaos.vulnerabilitiescache.cvss.v3d1;

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
    @Nonnull
    @JsonProperty("accessVector")
    private final Optional<AccessVector> accessVector;
    @Nonnull
    @JsonProperty("attackComplexity")
    private final Optional<AccessComplexity> attackComplexity;
    @Nonnull
    @JsonProperty("privilegesRequired")
    private final Optional<PrivilegesRequired> privilegesRequired;
    @Nonnull
    @JsonProperty("userInteraction")
    private final Optional<UserInteraction> userInteraction;
    @Nonnull
    @JsonProperty("scope")
    private final Optional<Scope> scope;
    @Nonnull
    @JsonProperty("confidentialityImpact")
    private final Optional<Cia> confidentialityImpact;
    @Nonnull
    @JsonProperty("integrityImpact")
    private final Optional<Cia> integrityImpact;
    @Nonnull
    @JsonProperty("availabilityImpact")
    private final Optional<Cia> availabilityImpact;
    @Nonnull
    @JsonProperty("baseScore")
    private final Score baseScore;
    @Nonnull
    @JsonProperty("baseSeverity")
    private final Severity baseSeverity;
    @Nonnull
    @JsonProperty("exploitCodeMaturity")
    private final Optional<ExploitCodeMaturity> exploitCodeMaturity;
    @Nonnull
    @JsonProperty("remediationLevel")
    private final Optional<RemediationLevel> remediationLevel;
    @Nonnull
    @JsonProperty("reportConfidence")
    private final Optional<Confidence> reportConfidence;
    @Nonnull
    @JsonProperty("temporalScore")
    private final Optional<Score> temporalScore;
    @Nonnull
    @JsonProperty("temporalSeverity")
    private final Optional<Severity> temporalSeverity;
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
    @JsonProperty("modifiedPrivilegesRequired")
    private final Optional<ModifiedPrivilegesRequired> modifiedPrivilegesRequired;
    @Nonnull
    @JsonProperty("modifiedUserInteraction")
    private final Optional<ModifiedUserInteraction> modifiedUserInteraction;
    @Nonnull
    @JsonProperty("modifiedScope")
    private final Optional<ModifiedScope> modifiedScope;
    @Nonnull
    @JsonProperty("modifiedConfidentialityImpact")
    private final Optional<ModifiedCia> modifiedConfidentialityImpact;
    @Nonnull
    @JsonProperty("modifiedIntegrityImpact")
    private final Optional<ModifiedCia> modifiedIntegrityImpact;
    @Nonnull
    @JsonProperty("modifiedAvailabilityImpact")
    private final Optional<ModifiedCia> modifiedAvailabilityImpact;
    @Nonnull
    @JsonProperty("environmentalScore")
    private final Optional<Score> environmentalScore;
    @Nonnull
    @JsonProperty("environmentalSeverity")
    private final Optional<Severity> environmentalSeverity;

    private Cvss(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.version = requireToBePresent(builder.version, "version");
        this.vectorString = requireToBePresent(builder.vectorString, "vectorString");
        this.accessVector = builder.accessVector;
        this.attackComplexity = builder.attackComplexity;
        this.privilegesRequired = builder.privilegesRequired;
        this.userInteraction = builder.userInteraction;
        this.scope = builder.scope;
        this.confidentialityImpact = builder.confidentialityImpact;
        this.integrityImpact = builder.integrityImpact;
        this.availabilityImpact = builder.availabilityImpact;
        this.baseScore = requireToBePresent(builder.baseScore, "baseScore");
        this.baseSeverity = requireToBePresent(builder.baseSeverity, "baseSeverity");
        this.exploitCodeMaturity = builder.exploitCodeMaturity;
        this.remediationLevel = builder.remediationLevel;
        this.reportConfidence = builder.reportConfidence;
        this.temporalScore = builder.temporalScore;
        this.temporalSeverity = builder.temporalSeverity;
        this.confidentialityRequirement = builder.confidentialityRequirement;
        this.integrityRequirement = builder.integrityRequirement;
        this.availabilityRequirement = builder.availabilityRequirement;
        this.modifiedAttackVector = builder.modifiedAttackVector;
        this.modifiedAttackComplexity = builder.modifiedAttackComplexity;
        this.modifiedPrivilegesRequired = builder.modifiedPrivilegesRequired;
        this.modifiedUserInteraction = builder.modifiedUserInteraction;
        this.modifiedScope = builder.modifiedScope;
        this.modifiedConfidentialityImpact = builder.modifiedConfidentialityImpact;
        this.modifiedIntegrityImpact = builder.modifiedIntegrityImpact;
        this.modifiedAvailabilityImpact = builder.modifiedAvailabilityImpact;
        this.environmentalScore = builder.environmentalScore;
        this.environmentalSeverity = builder.environmentalSeverity;
    }

    @Nonnull
    public static Builder newCvss() {
        return new Builder();
    }

    @Nonnull
    public static Builder newCvss31() {
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

    @JsonProperty("accessVector")
    @Nonnull
    public Optional<AccessVector> accessVector() {
        return accessVector;
    }

    @JsonProperty("attackComplexity")
    @Nonnull
    public Optional<AccessComplexity> attackComplexity() {
        return attackComplexity;
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

    @JsonProperty("scope")
    @Nonnull
    public Optional<Scope> scope() {
        return scope;
    }

    @JsonProperty("confidentialityImpact")
    @Nonnull
    public Optional<Cia> confidentialityImpact() {
        return confidentialityImpact;
    }

    @JsonProperty("integrityImpact")
    @Nonnull
    public Optional<Cia> integrityImpact() {
        return integrityImpact;
    }

    @JsonProperty("availabilityImpact")
    @Nonnull
    public Optional<Cia> availabilityImpact() {
        return availabilityImpact;
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

    @JsonProperty("exploitCodeMaturity")
    @Nonnull
    public Optional<ExploitCodeMaturity> exploitCodeMaturity() {
        return exploitCodeMaturity;
    }

    @JsonProperty("remediationLevel")
    @Nonnull
    public Optional<RemediationLevel> remediationLevel() {
        return remediationLevel;
    }

    @JsonProperty("reportConfidence")
    @Nonnull
    public Optional<Confidence> reportConfidence() {
        return reportConfidence;
    }

    @JsonProperty("temporalScore")
    @Nonnull
    public Optional<Score> temporalScore() {
        return temporalScore;
    }

    @JsonProperty("temporalSeverity")
    @Nonnull
    public Optional<Severity> temporalSeverity() {
        return temporalSeverity;
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

    @JsonProperty("modifiedScope")
    @Nonnull
    public Optional<ModifiedScope> modifiedScope() {
        return modifiedScope;
    }

    @JsonProperty("modifiedConfidentialityImpact")
    @Nonnull
    public Optional<ModifiedCia> modifiedConfidentialityImpact() {
        return modifiedConfidentialityImpact;
    }

    @JsonProperty("modifiedIntegrityImpact")
    @Nonnull
    public Optional<ModifiedCia> modifiedIntegrityImpact() {
        return modifiedIntegrityImpact;
    }

    @JsonProperty("modifiedAvailabilityImpact")
    @Nonnull
    public Optional<ModifiedCia> modifiedAvailabilityImpact() {
        return modifiedAvailabilityImpact;
    }

    @JsonProperty("environmentalScore")
    @Nonnull
    public Optional<Score> environmentalScore() {
        return environmentalScore;
    }

    @JsonProperty("environmentalSeverity")
    @Nonnull
    public Optional<Severity> environmentalSeverity() {
        return environmentalSeverity;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Version> version = Optional.empty();
        @Nonnull
        private Optional<VectorString> vectorString = Optional.empty();
        @Nonnull
        private Optional<AccessVector> accessVector = Optional.empty();
        @Nonnull
        private Optional<AccessComplexity> attackComplexity = Optional.empty();
        @Nonnull
        private Optional<PrivilegesRequired> privilegesRequired = Optional.empty();
        @Nonnull
        private Optional<UserInteraction> userInteraction = Optional.empty();
        @Nonnull
        private Optional<Scope> scope = Optional.empty();
        @Nonnull
        private Optional<Cia> confidentialityImpact = Optional.empty();
        @Nonnull
        private Optional<Cia> integrityImpact = Optional.empty();
        @Nonnull
        private Optional<Cia> availabilityImpact = Optional.empty();
        @Nonnull
        private Optional<Score> baseScore = Optional.empty();
        @Nonnull
        private Optional<Severity> baseSeverity = Optional.empty();
        @Nonnull
        private Optional<ExploitCodeMaturity> exploitCodeMaturity = Optional.empty();
        @Nonnull
        private Optional<RemediationLevel> remediationLevel = Optional.empty();
        @Nonnull
        private Optional<Confidence> reportConfidence = Optional.empty();
        @Nonnull
        private Optional<Score> temporalScore = Optional.empty();
        @Nonnull
        private Optional<Severity> temporalSeverity = Optional.empty();
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
        private Optional<ModifiedPrivilegesRequired> modifiedPrivilegesRequired = Optional.empty();
        @Nonnull
        private Optional<ModifiedUserInteraction> modifiedUserInteraction = Optional.empty();
        @Nonnull
        private Optional<ModifiedScope> modifiedScope = Optional.empty();
        @Nonnull
        private Optional<ModifiedCia> modifiedConfidentialityImpact = Optional.empty();
        @Nonnull
        private Optional<ModifiedCia> modifiedIntegrityImpact = Optional.empty();
        @Nonnull
        private Optional<ModifiedCia> modifiedAvailabilityImpact = Optional.empty();
        @Nonnull
        private Optional<Score> environmentalScore = Optional.empty();
        @Nonnull
        private Optional<Severity> environmentalSeverity = Optional.empty();

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

        @JsonProperty("accessVector")
        @Nonnull
        public Builder withAccessVector(@Nullable AccessVector v) {
            this.accessVector = ofNullable(v);
            return this;
        }

        @JsonProperty("attackComplexity")
        @Nonnull
        public Builder withAttackComplexity(@Nullable AccessComplexity v) {
            this.attackComplexity = ofNullable(v);
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

        @JsonProperty("scope")
        @Nonnull
        public Builder withScope(@Nullable Scope v) {
            this.scope = ofNullable(v);
            return this;
        }

        @JsonProperty("confidentialityImpact")
        @Nonnull
        public Builder withConfidentialityImpact(@Nullable Cia v) {
            this.confidentialityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("integrityImpact")
        @Nonnull
        public Builder withIntegrityImpact(@Nullable Cia v) {
            this.integrityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("availabilityImpact")
        @Nonnull
        public Builder withAvailabilityImpact(@Nullable Cia v) {
            this.availabilityImpact = ofNullable(v);
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

        @JsonProperty("exploitCodeMaturity")
        @Nonnull
        public Builder withExploitCodeMaturity(@Nullable ExploitCodeMaturity v) {
            this.exploitCodeMaturity = ofNullable(v);
            return this;
        }

        @JsonProperty("remediationLevel")
        @Nonnull
        public Builder withRemediationLevel(@Nullable RemediationLevel v) {
            this.remediationLevel = ofNullable(v);
            return this;
        }

        @JsonProperty("reportConfidence")
        @Nonnull
        public Builder withReportConfidence(@Nullable Confidence v) {
            this.reportConfidence = ofNullable(v);
            return this;
        }

        @JsonProperty("temporalScore")
        @Nonnull
        public Builder withTemporalScore(@Nullable Score v) {
            this.temporalScore = ofNullable(v);
            return this;
        }

        @JsonProperty("temporalSeverity")
        @Nonnull
        public Builder withTemporalSeverity(@Nullable Severity v) {
            this.temporalSeverity = ofNullable(v);
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

        @JsonProperty("modifiedScope")
        @Nonnull
        public Builder withModifiedScope(@Nullable ModifiedScope v) {
            this.modifiedScope = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedConfidentialityImpact")
        @Nonnull
        public Builder withModifiedConfidentialityImpact(@Nullable ModifiedCia v) {
            this.modifiedConfidentialityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedIntegrityImpact")
        @Nonnull
        public Builder withModifiedIntegrityImpact(@Nullable ModifiedCia v) {
            this.modifiedIntegrityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("modifiedAvailabilityImpact")
        @Nonnull
        public Builder withModifiedAvailabilityImpact(@Nullable ModifiedCia v) {
            this.modifiedAvailabilityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("environmentalScore")
        @Nonnull
        public Builder withEnvironmentalScore(@Nullable Score v) {
            this.environmentalScore = ofNullable(v);
            return this;
        }

        @JsonProperty("environmentalSeverity")
        @Nonnull
        public Builder withEnvironmentalSeverity(@Nullable Severity v) {
            this.environmentalSeverity = ofNullable(v);
            return this;
        }

        @Nonnull
        public Cvss build() {
            return new Cvss(this);
        }
    }
}
