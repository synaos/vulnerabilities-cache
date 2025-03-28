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

@JsonDeserialize(builder = SupportingMedia.Builder.class)
@ThreadSafe
@Immutable
public final class SupportingMedia {
    @Nonnull
    @JsonProperty(value = "type")
    private final SupportedMediaType type;
    @Nonnull
    @JsonProperty(value = "base64")
    private final Optional<Boolean> base64;
    @Nonnull
    @JsonProperty(value = "value")
    private final SupportedMediaValue value;

    private SupportingMedia(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.type = requireToBePresent(builder.type, "type");
        this.base64 = builder.base64;
        this.value = requireToBePresent(builder.value, "value");
    }

    @Nonnull
    public static Builder newSupportingMedia() {
        return new Builder();
    }

    @JsonProperty(value = "type", required = true)
    @Nonnull
    public SupportedMediaType type() {
        return type;
    }

    @JsonProperty("base64")
    @Nonnull
    public Optional<Boolean> base64() {
        return base64;
    }

    @JsonProperty(value = "value", required = true)
    @Nonnull
    public SupportedMediaValue value() {
        return value;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<SupportedMediaType> type = Optional.empty();
        @Nonnull
        private Optional<Boolean> base64 = Optional.empty();
        @Nonnull
        private Optional<SupportedMediaValue> value = Optional.empty();

        @JsonProperty(value = "type", required = true)
        @Nonnull
        public Builder withType(@Nonnull SupportedMediaType v) {
            this.type = ofNonNull(v, "type");
            return this;
        }

        @JsonProperty("base64")
        @Nonnull
        public Builder withBase64(@Nullable Boolean v) {
            this.base64 = ofNullable(v);
            return this;
        }

        @JsonProperty(value = "value", required = true)
        @Nonnull
        public Builder withValue(@Nonnull SupportedMediaValue v) {
            this.value = ofNonNull(v, "value");
            return this;
        }

        @Nonnull
        public SupportingMedia build() {
            return new SupportingMedia(this);
        }
    }
}
