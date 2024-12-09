package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Credits.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Credits.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Credits extends ListKind<TimelineRecord, Credits> {

    public Credits(@Nonnull List<TimelineRecord> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<TimelineRecord, Credits> {
        Serializer() {
            super(TimelineRecord.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<TimelineRecord, Credits> {
        Deserializer() {
            super(TimelineRecord.class, Credits::new);
        }
    }
}
