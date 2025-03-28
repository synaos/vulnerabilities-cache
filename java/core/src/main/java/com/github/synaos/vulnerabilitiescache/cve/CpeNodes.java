package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.CpeNodes.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.CpeNodes.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class CpeNodes extends ListKind<CpeNode, CpeNodes> {
    public CpeNodes(@Nonnull List<CpeNode> entries) {
        super(entries);
    }

    static class Serializer extends ListKind.Serializer<CpeNode, CpeNodes> {
        Serializer() {
            super(CpeNode.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<CpeNode, CpeNodes> {
        Deserializer() {
            super(CpeNode.class, CpeNodes::new);
        }
    }
}
