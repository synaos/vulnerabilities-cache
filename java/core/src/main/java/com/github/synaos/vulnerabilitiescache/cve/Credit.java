package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class Credit {
    @Nonnull
    @JsonProperty("lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty("value")
    private final CreditValue value;
    @Nonnull
    @JsonProperty("user")
    private final Optional<UUID> user;
    @Nonnull
    @JsonProperty("type")
    private final Optional<Type> type;

    public enum Type {
        @JsonProperty("finder")
        finder,
        @JsonProperty("reporter")
        reporter,
        @JsonProperty("analyst")
        analyst,
        @JsonProperty("coordinator")
        coordinator,
        @JsonProperty("remediation developer")
        remediationDeveloper,
        @JsonProperty("remediation reviewer")
        remediationReviewer,
        @JsonProperty("remediation verifier")
        remediationVerifier,
        @JsonProperty("tool")
        tool,
        @JsonProperty("sponsor")
        sponsor,
        @JsonProperty("other")
        other;
    }
}
