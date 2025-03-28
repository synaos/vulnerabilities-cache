package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.ProgramFiles.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.ProgramFiles.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class ProgramFiles extends ListKind<ProgramFile, ProgramFiles> {

    public ProgramFiles(@Nonnull List<ProgramFile> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<ProgramFile, ProgramFiles> {
        Serializer() {
            super(ProgramFile.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<ProgramFile, ProgramFiles> {
        Deserializer() {
            super(ProgramFile.class, ProgramFiles::new);
        }
    }
}
