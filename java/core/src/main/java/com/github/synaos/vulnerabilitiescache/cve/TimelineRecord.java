package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

@ThreadSafe
@Immutable
public final class TimelineRecord {
    @Nonnull
    @JsonProperty("time")
    private final ZonedDateTime time;
    @Nonnull
    @JsonProperty("lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty("value")
    private final TimelineRecordValue value;
}
