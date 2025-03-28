package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.Metrics.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.Metrics.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class Metrics extends ListKind<Metric, Metrics> {

    public Metrics(@Nonnull List<Metric> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<Metric, Metrics> {
        Serializer() {
            super(Metric.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<Metric, Metrics> {
        Deserializer() {
            super(Metric.class, Metrics::new);
        }
    }
}
