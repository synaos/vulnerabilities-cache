package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.TaxonomyMappings.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.TaxonomyMappings.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class TaxonomyMappings extends ListKind<TaxonomyMapping, TaxonomyMappings> {

    public TaxonomyMappings(@Nonnull List<TaxonomyMapping> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<TaxonomyMapping, TaxonomyMappings> {
        Serializer() {
            super(TaxonomyMapping.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<TaxonomyMapping, TaxonomyMappings> {
        Deserializer() {
            super(TaxonomyMapping.class, TaxonomyMappings::new);
        }
    }
}
