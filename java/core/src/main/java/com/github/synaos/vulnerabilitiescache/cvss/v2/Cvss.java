package com.github.synaos.vulnerabilitiescache.cvss.v2;

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
    @JsonProperty("accessComplexity")
    private final Optional<AccessVector> accessComplexity;
    @Nonnull
    @JsonProperty("authentication")
    private final Optional<Authentication> authentication;
    @Nonnull
    @JsonProperty("confidentialityImpact")
    private final Optional<CiaType> confidentialityImpact;
    @Nonnull
    @JsonProperty("integrityImpact")
    private final Optional<CiaType> integrityImpact;
    @Nonnull
    @JsonProperty("availabilityImpact")
    private final Optional<CiaType> availabilityImpact;
    @Nonnull
    @JsonProperty("baseScore")
    private final Score baseScore;
    @Nonnull
    @JsonProperty("exploitability")
    private final Optional<Exploitability> exploitability;
    @Nonnull
    @JsonProperty("remediationLevel")
    private final Optional<RemediationLevel> remediationLevel;
    @Nonnull
    @JsonProperty("reportConfidence")
    private final Optional<ReportConfidence> reportConfidence;
    @Nonnull
    @JsonProperty("temporalScore")
    private final Optional<Score> temporalScore;
    @Nonnull
    @JsonProperty("collateralDamagePotential")
    private final Optional<CollateralDamagePotential> collateralDamagePotential;
    @Nonnull
    @JsonProperty("targetDistribution")
    private final Optional<TargetDistribution> targetDistribution;
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
    @JsonProperty("environmentalScore")
    private final Optional<CiaRequirement> environmentalScore;

    private Cvss(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.version = requireToBePresent(builder.version, "version");
        this.vectorString = requireToBePresent(builder.vectorString, "vectorString");
        this.accessVector = builder.accessVector;
        this.accessComplexity = builder.accessComplexity;
        this.authentication = builder.authentication;
        this.confidentialityImpact = builder.confidentialityImpact;
        this.integrityImpact = builder.integrityImpact;
        this.availabilityImpact = builder.availabilityImpact;
        this.baseScore = requireToBePresent(builder.baseScore, "baseScore");
        this.exploitability = builder.exploitability;
        this.remediationLevel = builder.remediationLevel;
        this.reportConfidence = builder.reportConfidence;
        this.temporalScore = builder.temporalScore;
        this.collateralDamagePotential = builder.collateralDamagePotential;
        this.targetDistribution = builder.targetDistribution;
        this.confidentialityRequirement = builder.confidentialityRequirement;
        this.integrityRequirement = builder.integrityRequirement;
        this.availabilityRequirement = builder.availabilityRequirement;
        this.environmentalScore = builder.environmentalScore;
    }

    @Nonnull
    public static Builder newCvss() {
        return new Builder();
    }

    @Nonnull
    public static Builder newCvss2() {
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

    @JsonProperty("accessComplexity")
    @Nonnull
    public Optional<AccessVector> accessComplexity() {
        return accessComplexity;
    }

    @JsonProperty("authentication")
    @Nonnull
    public Optional<Authentication> authentication() {
        return authentication;
    }

    @JsonProperty("confidentialityImpact")
    @Nonnull
    public Optional<CiaType> confidentialityImpact() {
        return confidentialityImpact;
    }

    @JsonProperty("integrityImpact")
    @Nonnull
    public Optional<CiaType> integrityImpact() {
        return integrityImpact;
    }

    @JsonProperty("availabilityImpact")
    @Nonnull
    public Optional<CiaType> availabilityImpact() {
        return availabilityImpact;
    }

    @JsonProperty(value = "baseScore", required = true)
    @Nonnull
    public Score baseScore() {
        return baseScore;
    }

    @JsonProperty("exploitability")
    @Nonnull
    public Optional<Exploitability> exploitability() {
        return exploitability;
    }

    @JsonProperty("remediationLevel")
    @Nonnull
    public Optional<RemediationLevel> remediationLevel() {
        return remediationLevel;
    }

    @JsonProperty("reportConfidence")
    @Nonnull
    public Optional<ReportConfidence> reportConfidence() {
        return reportConfidence;
    }

    @JsonProperty("temporalScore")
    @Nonnull
    public Optional<Score> temporalScore() {
        return temporalScore;
    }

    @JsonProperty("collateralDamagePotential")
    @Nonnull
    public Optional<CollateralDamagePotential> collateralDamagePotential() {
        return collateralDamagePotential;
    }

    @JsonProperty("targetDistribution")
    @Nonnull
    public Optional<TargetDistribution> targetDistribution() {
        return targetDistribution;
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

    @JsonProperty("environmentalScore")
    @Nonnull
    public Optional<CiaRequirement> environmentalScore() {
        return environmentalScore;
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
        private Optional<AccessVector> accessComplexity = Optional.empty();
        @Nonnull
        private Optional<Authentication> authentication = Optional.empty();
        @Nonnull
        private Optional<CiaType> confidentialityImpact = Optional.empty();
        @Nonnull
        private Optional<CiaType> integrityImpact = Optional.empty();
        @Nonnull
        private Optional<CiaType> availabilityImpact = Optional.empty();
        @Nonnull
        private Optional<Score> baseScore = Optional.empty();
        @Nonnull
        private Optional<Exploitability> exploitability = Optional.empty();
        @Nonnull
        private Optional<RemediationLevel> remediationLevel = Optional.empty();
        @Nonnull
        private Optional<ReportConfidence> reportConfidence = Optional.empty();
        @Nonnull
        private Optional<Score> temporalScore = Optional.empty();
        @Nonnull
        private Optional<CollateralDamagePotential> collateralDamagePotential = Optional.empty();
        @Nonnull
        private Optional<TargetDistribution> targetDistribution = Optional.empty();
        @Nonnull
        private Optional<CiaRequirement> confidentialityRequirement = Optional.empty();
        @Nonnull
        private Optional<CiaRequirement> integrityRequirement = Optional.empty();
        @Nonnull
        private Optional<CiaRequirement> availabilityRequirement = Optional.empty();
        @Nonnull
        private Optional<CiaRequirement> environmentalScore = Optional.empty();

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

        @JsonProperty("accessComplexity")
        @Nonnull
        public Builder withAccessComplexity(@Nullable AccessVector v) {
            this.accessComplexity = ofNullable(v);
            return this;
        }

        @JsonProperty("authentication")
        @Nonnull
        public Builder withAuthentication(@Nullable Authentication v) {
            this.authentication = ofNullable(v);
            return this;
        }

        @JsonProperty("confidentialityImpact")
        @Nonnull
        public Builder withConfidentialityImpact(@Nullable CiaType v) {
            this.confidentialityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("integrityImpact")
        @Nonnull
        public Builder withIntegrityImpact(@Nullable CiaType v) {
            this.integrityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty("availabilityImpact")
        @Nonnull
        public Builder withAvailabilityImpact(@Nullable CiaType v) {
            this.availabilityImpact = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "baseScore", required = true)
        @Nonnull
        public Builder withBaseScore(@Nonnull Score v) {
            this.baseScore = ofNonNull(v, "baseScore");
            return this;
        }

        @JsonProperty("exploitability")
        @Nonnull
        public Builder withExploitability(@Nullable Exploitability v) {
            this.exploitability = ofNullable(v);
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
        public Builder withReportConfidence(@Nullable ReportConfidence v) {
            this.reportConfidence = ofNullable(v);
            return this;
        }

        @JsonProperty("temporalScore")
        @Nonnull
        public Builder withTemporalScore(@Nullable Score v) {
            this.temporalScore = ofNullable(v);
            return this;
        }

        @JsonProperty("collateralDamagePotential")
        @Nonnull
        public Builder withCollateralDamagePotential(@Nullable CollateralDamagePotential v) {
            this.collateralDamagePotential = ofNullable(v);
            return this;
        }

        @JsonProperty("targetDistribution")
        @Nonnull
        public Builder withTargetDistribution(@Nullable TargetDistribution v) {
            this.targetDistribution = ofNullable(v);
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

        @JsonProperty("environmentalScore")
        @Nonnull
        public Builder withEnvironmentalScore(@Nullable CiaRequirement v) {
            this.environmentalScore = ofNullable(v);
            return this;
        }

        @Nonnull
        public Cvss build() {
            return new Cvss(this);
        }
    }
}

