package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.AdpTags.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.AdpTags.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class AdpTags extends ListKind<TagExtension.Adp, AdpTags> {

    public AdpTags(@Nonnull List<TagExtension.Adp> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<TagExtension.Adp, AdpTags> {
        Serializer() {
            super(TagExtension.Adp.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<TagExtension.Adp, AdpTags> {
        Deserializer() {
            super(TagExtension.Adp.class, AdpTags::new);
        }
    }
}
