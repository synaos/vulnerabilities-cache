package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.CnaTags.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.CnaTags.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class CnaTags extends ListKind<TagExtension.Cna, CnaTags> {

    public CnaTags(@Nonnull List<TagExtension.Cna> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<TagExtension.Cna, CnaTags> {
        Serializer() {
            super(TagExtension.Cna.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<TagExtension.Cna, CnaTags> {
        Deserializer() {
            super(TagExtension.Cna.class, CnaTags::new);
        }
    }
}
