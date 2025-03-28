package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Impacts.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Impacts.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Impacts extends ListKind<Impact, Impacts> {

    public Impacts(@Nonnull List<Impact> entries) {
        super(1, 512, entries);
    }

    static class Serializer extends ListKind.Serializer<Impact, Impacts> {
        Serializer() {
            super(Impact.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Impact, Impacts> {
        Deserializer() {
            super(Impact.class, Impacts::new);
        }
    }
}
