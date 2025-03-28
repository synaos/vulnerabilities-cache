package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.Id;
import com.github.synaos.vulnerabilitiescache.Id.Cpe;
import com.github.synaos.vulnerabilitiescache.cve.CpeNames.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.CpeNames.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class CpeNames extends ListKind<CpeName, CpeNames> {
    public CpeNames(@Nonnull List<CpeName> entries) {
        super(0, 2800, entries); // By spec this should be 2048, but there are items with more...
    }

    static class Serializer extends ListKind.Serializer<CpeName, CpeNames> {
        Serializer() {
            super(CpeName.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<CpeName, CpeNames> {
        Deserializer() {
            super(CpeName.class, CpeNames::new);
        }
    }
}
