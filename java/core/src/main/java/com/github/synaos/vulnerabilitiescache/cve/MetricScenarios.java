package com.github.synaos.vulnerabilitiescache.cve;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.cve.MetricScenarios.Deserializer;
import com.github.synaos.vulnerabilitiescache.cve.MetricScenarios.Serializer;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

@ThreadSafe
@Immutable
@JsonSerialize(using = Serializer.class)
@JsonDeserialize(using = Deserializer.class)
public final class MetricScenarios extends ListKind<MetricScenario, MetricScenarios> {

    public MetricScenarios(@Nonnull List<MetricScenario> entries) {
        super(1, null, entries);
    }

    static class Serializer extends ListKind.Serializer<MetricScenario, MetricScenarios> {
        Serializer() {
            super(MetricScenario.class);
        }
    }

    static class Deserializer extends ListKind.Deserializer<MetricScenario, MetricScenarios> {
        Deserializer() {
            super(MetricScenario.class, MetricScenarios::new);
        }
    }
}
