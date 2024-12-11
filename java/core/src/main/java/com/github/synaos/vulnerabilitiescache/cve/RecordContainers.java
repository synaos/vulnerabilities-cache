package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = RecordContainers.Builder.class)
@ThreadSafe
@Immutable
public final class RecordContainers {

    @Nonnull
    @JsonProperty(value = "cna")
    private final CnaContainer cna;
    @Nonnull
    @JsonProperty(value = "adp")
    private final Optional<AdpContainers> adp;

    private RecordContainers(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.cna = requireToBePresent(builder.cna, "cna");
        this.adp = builder.adp;
    }


    @Nonnull
    public static Builder newRecordContainers() {
        return new Builder();
    }

    @JsonProperty(value = "cna", required = true)
    @Nonnull
    public CnaContainer cna() {
        return cna;
    }

    @JsonProperty("adp")
    @Nonnull
    public Optional<AdpContainers> adp() {
        return adp;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<CnaContainer> cna = Optional.empty();
        @Nonnull
        private Optional<AdpContainers> adp = Optional.empty();

        @JsonProperty(value = "cna", required = true)
        @Nonnull
        public Builder withCna(@Nonnull CnaContainer v) {
            this.cna = ofNonNull(v, "cna");
            return this;
        }

        @JsonProperty("adp")
        @Nonnull
        public Builder withAdp(@Nullable AdpContainers v) {
            this.adp = ofNullable(v);
            return this;
        }

        @Nonnull
        public RecordContainers build() {
            return new RecordContainers(this);
        }
    }
}
