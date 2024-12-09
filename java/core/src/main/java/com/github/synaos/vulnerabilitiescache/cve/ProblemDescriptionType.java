package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


public final class ProblemDescriptionType extends StringKind<ProblemDescriptionType> {

    @JsonCreator
    @Nonnull
    public static ProblemDescriptionType module(@Nonnull String v) {
        return new ProblemDescriptionType(v);
    }

    private ProblemDescriptionType(@Nonnull String value) {
        super(1, 128, value);
    }

}