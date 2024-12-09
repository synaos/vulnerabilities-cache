package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Workarounds.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Workarounds.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Workarounds extends ListKind<Description, Workarounds> {

    public Workarounds(@Nonnull List<Description> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Description, Workarounds> {
        Serializer() {
            super(Description.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Description, Workarounds> {
        Deserializer() {
            super(Description.class, Workarounds::new);
        }
    }
}
