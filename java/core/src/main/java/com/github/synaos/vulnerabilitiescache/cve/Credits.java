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
public final class Credits extends ListKind<Credit, Credits> {

    public Credits(@Nonnull List<Credit> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Credit, Credits> {
        Serializer() {
            super(Credit.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Credit, Credits> {
        Deserializer() {
            super(Credit.class, Credits::new);
        }
    }
}
