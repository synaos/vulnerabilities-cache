package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.ProblemTypes.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.ProblemTypes.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class ProblemTypes extends ListKind<ProblemType, ProblemTypes> {

    public ProblemTypes(@Nonnull List<ProblemType> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<ProblemType, ProblemTypes> {
        Serializer() {
            super(ProblemType.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<ProblemType, ProblemTypes> {
        Deserializer() {
            super(ProblemType.class, ProblemTypes::new);
        }
    }
}
