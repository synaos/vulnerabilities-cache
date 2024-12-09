package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.ModuleNames.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.ModuleNames.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class ModuleNames extends ListKind<ModuleName, ModuleNames> {

    public ModuleNames(@Nonnull List<ModuleName> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<ModuleName, ModuleNames> {
        Serializer() {
            super(ModuleName.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<ModuleName, ModuleNames> {
        Deserializer() {
            super(ModuleName.class, ModuleNames::new);
        }
    }
}
