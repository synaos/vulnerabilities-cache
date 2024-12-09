package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Products.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Products.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Products extends ListKind<Product, Products> {

    public Products(@Nonnull List<Product> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Product, Products> {

        Serializer() {
            super(Product.class);
        }

    }

    static class Deserializer extends ListKind.Deserializer<Product, Products> {

        Deserializer() {
            super(Product.class, Products::new);
        }

    }
}
