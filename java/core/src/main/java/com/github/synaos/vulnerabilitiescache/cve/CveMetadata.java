package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.synaos.vulnerabilitiescache.Id;

@JsonDeserialize(builder = CveMetadata.Builder.class)
@ThreadSafe
@Immutable
public final class CveMetadata implements Comparable<CveMetadata> {

    @Override
    public int compareTo(@Nonnull CveMetadata o) {
        requireNonNull(o, "o");
        return cveId.compareTo(o.cveId);
    }

    @Override
    public String toString() {
        return cveId.toString();
    }

    @Nonnull
    @JsonProperty(value = "cveId")
    private final Id.Cve cveId;
    @Nonnull
    @JsonProperty(value = "assignerOrgId")
    private final UUID assignerOrgId;
    @Nonnull
    @JsonProperty(value = "assignerShortName")
    private final Optional<ShortName> assignerShortName;
    @Nonnull
    @JsonProperty(value = "requesterUserId")
    private final Optional<UUID> requesterUserId;
    @Nonnull
    @JsonProperty(value = "dateUpdated")
    private final Optional<ZonedDateTime> dateUpdated;
    @Nonnull
    @JsonProperty(value = "serial")
    private final Optional<Integer> serial;
    @Nonnull
    @JsonProperty(value = "dateReserved")
    private final Optional<ZonedDateTime> dateReserved;
    @Nonnull
    @JsonProperty(value = "datePublished")
    private final Optional<ZonedDateTime> datePublished;
    @Nonnull
    @JsonProperty(value = "dateRejected")
    private final Optional<ZonedDateTime> dateRejected;
    @Nonnull
    @JsonProperty(value = "state")
    private final State state;

    private CveMetadata(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.cveId = requireToBePresent(builder.cveId, "cveId");
        this.assignerOrgId = requireToBePresent(builder.assignerOrgId, "assignerOrgId");
        this.assignerShortName = builder.assignerShortName;
        this.requesterUserId = builder.requesterUserId;
        this.dateUpdated = builder.dateUpdated;
        this.serial = builder.serial;
        this.dateReserved = builder.dateReserved;
        this.datePublished = builder.datePublished;
        this.dateRejected = builder.dateRejected;
        this.state = requireToBePresent(builder.state, "state");
    }

    @Nonnull
    public static Builder newCveMetadata() {
        return new Builder();
    }

    @JsonProperty(value = "cveId", required = true)
    @Nonnull
    public Id.Cve cveId() {
        return cveId;
    }

    @JsonProperty(value = "assignerOrgId", required = true)
    @Nonnull
    public UUID assignerOrgId() {
        return assignerOrgId;
    }

    @JsonProperty("assignerShortName")
    @Nonnull
    public Optional<ShortName> assignerShortName() {
        return assignerShortName;
    }

    @JsonProperty("requesterUserId")
    @Nonnull
    public Optional<UUID> requesterUserId() {
        return requesterUserId;
    }

    @JsonProperty("dateUpdated")
    @Nonnull
    public Optional<ZonedDateTime> dateUpdated() {
        return dateUpdated;
    }

    @JsonProperty("serial")
    @Nonnull
    public Optional<Integer> serial() {
        return serial;
    }

    @JsonProperty("dateReserved")
    @Nonnull
    public Optional<ZonedDateTime> dateReserved() {
        return dateReserved;
    }

    @JsonProperty("datePublished")
    @Nonnull
    public Optional<ZonedDateTime> datePublished() {
        return datePublished;
    }

    @JsonProperty("dateRejected")
    @Nonnull
    public Optional<ZonedDateTime> dateRejected() {
        return dateRejected;
    }

    @JsonProperty(value = "state", required = true)
    @Nonnull
    public State state() {
        return state;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Id.Cve> cveId = Optional.empty();
        @Nonnull
        private Optional<UUID> assignerOrgId = Optional.empty();
        @Nonnull
        private Optional<ShortName> assignerShortName = Optional.empty();
        @Nonnull
        private Optional<UUID> requesterUserId = Optional.empty();
        @Nonnull
        private Optional<ZonedDateTime> dateUpdated = Optional.empty();
        @Nonnull
        private Optional<Integer> serial = Optional.empty();
        @Nonnull
        private Optional<ZonedDateTime> dateReserved = Optional.empty();
        @Nonnull
        private Optional<ZonedDateTime> datePublished = Optional.empty();
        @Nonnull
        private Optional<ZonedDateTime> dateRejected = Optional.empty();
        @Nonnull
        private Optional<State> state = Optional.empty();

        @JsonProperty(value = "cveId", required = true)
        @Nonnull
        public Builder withCveId(@Nonnull Id.Cve v) {
            this.cveId = ofNonNull(v, "cveId");
            return this;
        }

        @JsonProperty(value = "assignerOrgId", required = true)
        @Nonnull
        public Builder withAssignerOrgId(@Nonnull UUID v) {
            this.assignerOrgId = ofNonNull(v, "assignerOrgId");
            return this;
        }

        @JsonProperty("assignerShortName")
        @Nonnull
        public Builder withAssignerShortName(@Nullable ShortName v) {
            this.assignerShortName = ofNullable(v);
            return this;
        }

        @JsonProperty("requesterUserId")
        @Nonnull
        public Builder withRequesterUserId(@Nullable UUID v) {
            this.requesterUserId = ofNullable(v);
            return this;
        }

        @JsonProperty("dateUpdated")
        @Nonnull
        public Builder withDateUpdated(@Nullable ZonedDateTime v) {
            this.dateUpdated = ofNullable(v);
            return this;
        }

        @JsonProperty("serial")
        @Nonnull
        public Builder withSerial(@Nullable Integer v) {
            this.serial = ofNullable(v);
            return this;
        }

        @JsonProperty("dateReserved")
        @Nonnull
        public Builder withDateReserved(@Nullable ZonedDateTime v) {
            this.dateReserved = ofNullable(v);
            return this;
        }

        @JsonProperty("datePublished")
        @Nonnull
        public Builder withDatePublished(@Nullable ZonedDateTime v) {
            this.datePublished = ofNullable(v);
            return this;
        }

        @JsonProperty("dateRejected")
        @Nonnull
        public Builder withDateRejected(@Nullable ZonedDateTime v) {
            this.dateRejected = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "state", required = true)
        @Nonnull
        public Builder withState(@Nonnull State v) {
            this.state = ofNonNull(v, "state");
            return this;
        }

        @Nonnull
        public CveMetadata build() {
            return new CveMetadata(this);
        }
    }
}
