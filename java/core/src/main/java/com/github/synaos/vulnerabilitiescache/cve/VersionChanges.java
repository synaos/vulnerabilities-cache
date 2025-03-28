package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.VersionChanges.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.VersionChanges.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class VersionChanges extends ListKind<VersionChange, VersionChanges> {

    public VersionChanges(@Nonnull List<VersionChange> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<VersionChange, VersionChanges> {
        Serializer() {
            super(VersionChange.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<VersionChange, VersionChanges> {
        Deserializer() {
            super(VersionChange.class, VersionChanges::new);
        }
    }
}
