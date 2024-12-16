package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class ProblemDescriptionText extends StringKind<ProblemDescriptionText> {

    @JsonCreator
    @Nonnull
    public static ProblemDescriptionText problemDescriptionText(@Nonnull String v) {
        return new ProblemDescriptionText(v);
    }

    private ProblemDescriptionText(@Nonnull String value) {
        super(1, 4096, value);
    }

}
