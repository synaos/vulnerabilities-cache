package com.github.synaos.vulnerabilitiescache.cvss.v3d1;

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

}
