package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Timeline.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Timeline.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Timeline extends ListKind<TimelineRecord, Timeline> {

    public Timeline(@Nonnull List<TimelineRecord> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<TimelineRecord, Timeline> {
        Serializer() {
            super(TimelineRecord.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<TimelineRecord, Timeline> {
        Deserializer() {
            super(TimelineRecord.class, Timeline::new);
        }
    }
}
