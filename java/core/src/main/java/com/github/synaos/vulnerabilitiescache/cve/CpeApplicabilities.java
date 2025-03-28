package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.CpeApplicabilities.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.CpeApplicabilities.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class CpeApplicabilities extends ListKind<CpeApplicability, CpeApplicabilities> {
    public CpeApplicabilities(@Nonnull List<CpeApplicability> entries) {
        super(entries);
    }

    static class Serializer extends ListKind.Serializer<CpeApplicability, CpeApplicabilities> {
        Serializer() {
            super(CpeApplicability.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<CpeApplicability, CpeApplicabilities> {
        Deserializer() {
            super(CpeApplicability.class, CpeApplicabilities::new);
        }
    }
}
