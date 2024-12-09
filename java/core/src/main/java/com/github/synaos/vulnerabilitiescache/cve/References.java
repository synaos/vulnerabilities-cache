package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.References.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.References.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class References extends ListKind<Reference, References> {

    public References(@Nonnull List<Reference> entries) {
        super(1, 512, entries);
    }

    static class Serializer extends ListKind.Serializer<Reference, References> {
        Serializer() {
            super(Reference.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Reference, References> {
        Deserializer() {
            super(Reference.class, References::new);
        }
    }
}
