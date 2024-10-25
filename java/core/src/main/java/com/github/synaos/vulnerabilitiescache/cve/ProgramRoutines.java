package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.ProgramRoutines.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.ProgramRoutines.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class ProgramRoutines extends ListKind<ProgramRoutine, ProgramRoutines> {

    public ProgramRoutines(@Nonnull List<ProgramRoutine> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<ProgramRoutine, ProgramRoutines> {
        Serializer() {
            super(ProgramRoutine.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<ProgramRoutine, ProgramRoutines> {
        Deserializer() {
            super(ProgramRoutine.class, ProgramRoutines::new);
        }
    }
}
