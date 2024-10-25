package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Tags.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Tags.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Tags extends ListKind<Tag, Tags> {

    public Tags(@Nonnull List<Tag> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Tag, Tags> {
        Serializer() {
            super(Tag.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Tag, Tags> {
        Deserializer() {
            super(Tag.class, Tags::new);
        }
    }
}
