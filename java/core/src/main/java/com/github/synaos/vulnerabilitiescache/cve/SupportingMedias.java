package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.SupportingMedias.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.SupportingMedias.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class SupportingMedias extends ListKind<SupportingMedia, SupportingMedias> {

    public SupportingMedias(@Nonnull List<SupportingMedia> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<SupportingMedia, SupportingMedias> {
        Serializer() {
            super(SupportingMedia.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<SupportingMedia, SupportingMedias> {
        Deserializer() {
            super(SupportingMedia.class, SupportingMedias::new);
        }
    }
}
