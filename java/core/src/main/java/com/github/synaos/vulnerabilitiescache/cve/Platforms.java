package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Platforms.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Platforms.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Platforms extends ListKind<Platform, Platforms> {

    public Platforms(@Nonnull List<Platform> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Platform, Platforms> {
        Serializer() {
            super(Platform.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Platform, Platforms> {
        Deserializer() {
            super(Platform.class, Platforms::new);
        }
    }
}
