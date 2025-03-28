package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.TaxonomyRelations.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.TaxonomyRelations.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class TaxonomyRelations extends ListKind<TaxonomyRelation, TaxonomyRelations> {

    public TaxonomyRelations(@Nonnull List<TaxonomyRelation> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<TaxonomyRelation, TaxonomyRelations> {
        Serializer() {
            super(TaxonomyRelation.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<TaxonomyRelation, TaxonomyRelations> {
        Deserializer() {
            super(TaxonomyRelation.class, TaxonomyRelations::new);
        }
    }
}
