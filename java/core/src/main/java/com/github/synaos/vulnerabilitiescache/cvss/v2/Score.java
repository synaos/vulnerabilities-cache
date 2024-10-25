package com.github.synaos.vulnerabilitiescache.cvss.v2;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.NumberKind;


public final class Score extends NumberKind<Integer, Score> {

    @JsonCreator
    @Nonnull
    public static Score vectorString(int v) {
        return new Score(v);
    }

    private Score(int value) {
        super(Integer::compare, 0, 10, value);
    }

}
