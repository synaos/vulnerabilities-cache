package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class ProblemType {
    @JsonProperty("descriptions")
    private final ProblemDescriptions descriptions;
}
