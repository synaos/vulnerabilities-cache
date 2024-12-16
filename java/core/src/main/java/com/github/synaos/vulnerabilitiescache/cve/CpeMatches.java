package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.CpeMatches.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.CpeMatches.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class CpeMatches extends ListKind<CpeMatch, CpeMatches> {
    public CpeMatches(@Nonnull List<CpeMatch> entries) {
        super(entries);
    }

    static class Serializer extends ListKind.Serializer<CpeMatch, CpeMatches> {
        Serializer() {
            super(CpeMatch.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<CpeMatch, CpeMatches> {
        Deserializer() {
            super(CpeMatch.class, CpeMatches::new);
        }
    }
}
