package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class TimelineRecordValue extends StringKind<TimelineRecordValue> {

    @JsonCreator
    @Nonnull
    public static TimelineRecordValue timelineRecordValue(@Nonnull String v) {
        return new TimelineRecordValue(v);
    }

    private TimelineRecordValue(@Nonnull String value) {
        super(1, 4096, value);
    }

}
