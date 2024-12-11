package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.VersionRefs.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.VersionRefs.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class VersionRefs extends ListKind<VersionRef, VersionRefs> {

    public VersionRefs(@Nonnull List<VersionRef> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<VersionRef, VersionRefs> {
        Serializer() {
            super(VersionRef.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<VersionRef, VersionRefs> {
        Deserializer() {
            super(VersionRef.class, VersionRefs::new);
        }
    }
}
