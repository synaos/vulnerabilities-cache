package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Configurations.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Configurations.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Configurations extends ListKind<Description, Configurations> {

    public Configurations(@Nonnull List<Description> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Description, Configurations> {
        Serializer() {
            super(Description.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Description, Configurations> {
        Deserializer() {
            super(Description.class, Configurations::new);
        }
    }
}
