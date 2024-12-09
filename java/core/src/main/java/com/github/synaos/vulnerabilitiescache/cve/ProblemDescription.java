package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Locale;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.synaos.vulnerabilitiescache.Id.Cwe;

@ThreadSafe
@Immutable
public final class ProblemDescription {
    @Nonnull
    @JsonProperty("lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty("description")
    private final ProblemDescriptionText description;
    @Nonnull
    @JsonProperty("cweId")
    private final Optional<Cwe> cweId;
    @Nonnull
    @JsonProperty("type")
    private final Optional<ProblemDescriptionType> type;
    @Nonnull
    @JsonProperty("references")
    private final Optional<References> references;
}
