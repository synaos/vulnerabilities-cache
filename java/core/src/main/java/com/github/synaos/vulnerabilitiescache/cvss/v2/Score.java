package com.github.synaos.vulnerabilitiescache.cvss.v2;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.NumberKind;

@ThreadSafe
@Immutable
public final class Score extends NumberKind<Integer, Score> {

    @JsonCreator
    @Nonnull
    public static Score score(int v) {
        return new Score(v);
    }

    private Score(int value) {
        super(Integer::compare, 0, 10, value);
    }

}
