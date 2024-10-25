package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ProblemType {
    @JsonProperty("descriptions")
    private final ProblemDescriptions descriptions;
}
