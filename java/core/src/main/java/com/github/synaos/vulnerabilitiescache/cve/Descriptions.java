package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Descriptions.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Descriptions.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Descriptions extends ListKind<Description, Descriptions> {

    public Descriptions(@Nonnull List<Description> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Description, Descriptions> {
        Serializer() {
            super(Description.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Description, Descriptions> {
        Deserializer() {
            super(Description.class, Descriptions::new);
        }
    }
}
