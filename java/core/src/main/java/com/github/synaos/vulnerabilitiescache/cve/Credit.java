package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Credit.Builder.class)
@ThreadSafe
@Immutable
public final class Credit {
    @Nonnull
    @JsonProperty("lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty("value")
    private final CreditValue value;
    @Nonnull
    @JsonProperty("user")
    private final Optional<UUID> user;
    @Nonnull
    @JsonProperty("type")
    private final Optional<Type> type;

    private Credit(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.lang = requireToBePresent(builder.lang, "lang");
        this.value = requireToBePresent(builder.value, "value");
        this.user = builder.user;
        this.type = builder.type;
    }

    @Nonnull
    public static Builder newCredit() {
        return new Builder();
    }

    @JsonProperty(value = "lang", required = true)
    @Nonnull
    public Locale lang() {
        return lang;
    }

    @JsonProperty(value = "value", required = true)
    @Nonnull
    public CreditValue value() {
        return value;
    }

    @JsonProperty("user")
    @Nonnull
    public Optional<UUID> user() {
        return user;
    }

    @JsonProperty("type")
    @Nonnull
    public Optional<Type> type() {
        return type;
    }


    public enum Type {
        @JsonProperty("finder")
        finder,
        @JsonProperty("reporter")
        reporter,
        @JsonProperty("analyst")
        analyst,
        @JsonProperty("coordinator")
        coordinator,
        @JsonProperty("remediation developer")
        remediationDeveloper,
        @JsonProperty("remediation reviewer")
        remediationReviewer,
        @JsonProperty("remediation verifier")
        remediationVerifier,
        @JsonProperty("tool")
        tool,
        @JsonProperty("sponsor")
        sponsor,
        @JsonProperty("other")
        other
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Locale> lang = Optional.empty();
        @Nonnull
        private Optional<CreditValue> value = Optional.empty();
        @Nonnull
        private Optional<UUID> user = Optional.empty();
        @Nonnull
        private Optional<Type> type = Optional.empty();

        @JsonProperty(value = "lang", required = true)
        @Nonnull
        public Builder withLang(@Nonnull Locale v) {
            this.lang = ofNonNull(v, "lang");
            return this;
        }

        @JsonProperty(value = "value", required = true)
        @Nonnull
        public Builder withValue(@Nonnull CreditValue v) {
            this.value = ofNonNull(v, "value");
            return this;
        }

        @JsonProperty("user")
        @Nonnull
        public Builder withUser(@Nullable UUID v) {
            this.user = ofNullable(v);
            return this;
        }

        @JsonProperty("type")
        @Nonnull
        public Builder withType(@Nullable Type v) {
            this.type = ofNullable(v);
            return this;
        }

        @Nonnull
        public Credit build() {
            return new Credit(this);
        }
    }
}
