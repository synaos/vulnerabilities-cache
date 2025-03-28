package com.github.synaos.vulnerabilitiescache.cvss.v2;

import static java.lang.String.format;

import java.math.BigDecimal;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.NumberKind;

@ThreadSafe
@Immutable
public final class Score extends NumberKind<BigDecimal, Score> {

    @JsonCreator
    @Nonnull
    public static Score score(double v) {
        return new Score(BigDecimal.valueOf(v));
    }

    @JsonCreator
    @Nonnull
    public static Score score(BigDecimal v) {
        return new Score(v);
    }

    private Score(BigDecimal value) {
        super(BigDecimal::compareTo, BigDecimal.ZERO, BigDecimal.TEN, value);
        if (value.scale() > 1) {
            throw new IllegalArgumentException(format("'%s' is expected to have not more than 1 decimal position; but has %d.", value, value.scale()));
        }
    }

}
