package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.ProblemDescriptions.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.ProblemDescriptions.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class ProblemDescriptions extends ListKind<ProblemDescription, ProblemDescriptions> {

    public ProblemDescriptions(@Nonnull List<ProblemDescription> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<ProblemDescription, ProblemDescriptions> {
        Serializer() {
            super(ProblemDescription.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<ProblemDescription, ProblemDescriptions> {
        Deserializer() {
            super(ProblemDescription.class, ProblemDescriptions::new);
        }
    }
}
