package com.github.synaos.vulnerabilitiescache.cvss.v2;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

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

}

