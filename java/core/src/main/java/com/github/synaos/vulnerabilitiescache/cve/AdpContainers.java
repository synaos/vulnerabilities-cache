package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.AdpContainers.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.AdpContainers.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class AdpContainers extends ListKind<AdpContainer, AdpContainers> {

    public AdpContainers(@Nonnull List<AdpContainer> entries) {
        super(1, 512, entries);
    }

    static class Serializer extends ListKind.Serializer<AdpContainer, AdpContainers> {
        Serializer() {
            super(AdpContainer.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<AdpContainer, AdpContainers> {
        Deserializer() {
            super(AdpContainer.class, AdpContainers::new);
        }
    }
}
