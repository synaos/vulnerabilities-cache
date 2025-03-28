package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;

@JsonDeserialize(builder = TimelineRecord.Builder.class)
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

    private TimelineRecord(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.time = requireToBePresent(builder.time, "time");
        this.lang = requireToBePresent(builder.lang, "lang");
        this.value = requireToBePresent(builder.value, "value");
    }


    @Nonnull
    public static Builder newTimelineRecord() {
        return new Builder();
    }

    @JsonProperty(value = "time", required = true)
    @Nonnull
    public ZonedDateTime time() {
        return time;
    }

    @JsonProperty(value = "lang", required = true)
    @Nonnull
    public Locale lang() {
        return lang;
    }

    @JsonProperty(value = "value", required = true)
    @Nonnull
    public TimelineRecordValue value() {
        return value;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<ZonedDateTime> time = Optional.empty();
        @Nonnull
        private Optional<Locale> lang = Optional.empty();
        @Nonnull
        private Optional<TimelineRecordValue> value = Optional.empty();

        @JsonProperty(value = "time", required = true)
        @Nonnull
        public Builder withTime(@Nonnull ZonedDateTime v) {
            this.time = ofNonNull(v, "time");
            return this;
        }

        @JsonProperty(value = "lang", required = true)
        @Nonnull
        public Builder withLang(@Nonnull Locale v) {
            this.lang = ofNonNull(v, "lang");
            return this;
        }

        @JsonProperty(value = "value", required = true)
        @Nonnull
        public Builder withValue(@Nonnull TimelineRecordValue v) {
            this.value = ofNonNull(v, "value");
            return this;
        }

        @Nonnull
        public TimelineRecord build() {
            return new TimelineRecord(this);
        }
    }
}
