package com.github.synaos.vulnerabilitiescache.cvss.v4;

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
    @JsonProperty("baseScore")
    private final Score baseScore;
    @Nonnull
    @JsonProperty("baseSeverity")
    private final Severity baseSeverity;
    @Nonnull
    @JsonProperty("accessVector")
    private final Optional<AccessVector> accessVector;
    @Nonnull
    @JsonProperty("attackComplexity")
    private final Optional<AccessComplexity> attackComplexity;
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

}
