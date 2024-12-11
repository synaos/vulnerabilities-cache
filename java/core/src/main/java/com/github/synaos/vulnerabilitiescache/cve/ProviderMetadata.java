package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = ProviderMetadata.Builder.class)
@ThreadSafe
@Immutable
public final class ProviderMetadata {

    @Nonnull
    @JsonProperty("orgId")
    private final UUID orgId;
    @Nonnull
    @JsonProperty("shortName")
    private final Optional<String> shortName;
    @Nonnull
    @JsonProperty("dateUpdated")
    private final Optional<ZonedDateTime> dateUpdated;

    private ProviderMetadata(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.orgId = requireToBePresent(builder.orgId, "orgId");
        this.shortName = builder.shortName;
        this.dateUpdated = builder.dateUpdated;
    }


    @Nonnull
    public static Builder newProviderMetadata() {
        return new Builder();
    }

    @JsonProperty(value = "orgId", required = true)
    @Nonnull
    public UUID orgId() {
        return orgId;
    }

    @JsonProperty("shortName")
    @Nonnull
    public Optional<String> shortName() {
        return shortName;
    }

    @JsonProperty("dateUpdated")
    @Nonnull
    public Optional<ZonedDateTime> dateUpdated() {
        return dateUpdated;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<UUID> orgId = Optional.empty();
        @Nonnull
        private Optional<String> shortName = Optional.empty();
        @Nonnull
        private Optional<ZonedDateTime> dateUpdated = Optional.empty();

        @JsonProperty(value = "orgId", required = true)
        @Nonnull
        public Builder withOrgId(@Nonnull UUID v) {
            this.orgId = ofNonNull(v, "orgId");
            return this;
        }

        @JsonProperty("shortName")
        @Nonnull
        public Builder withShortName(@Nullable String v) {
            this.shortName = ofNullable(v);
            return this;
        }

        @JsonProperty("dateUpdated")
        @Nonnull
        public Builder withDateUpdated(@Nullable ZonedDateTime v) {
            this.dateUpdated = ofNullable(v);
            return this;
        }

        @Nonnull
        public ProviderMetadata build() {
            return new ProviderMetadata(this);
        }
    }
}
