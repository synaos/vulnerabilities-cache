package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Locale;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = Description.Builder.class)
@ThreadSafe
@Immutable
public final class Description {

    @Nonnull
    @JsonProperty(value = "lang")
    private final Locale lang;
    @Nonnull
    @JsonProperty(value = "value")
    private final DescriptionValue value;
    @Nonnull
    @JsonProperty(value = "supportingMedia")
    private final Optional<SupportingMedias> supportingMedia;

    private Description(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.lang = requireToBePresent(builder.lang, "lang");
        this.value = requireToBePresent(builder.value, "value");
        this.supportingMedia = builder.supportingMedia;
    }

    @Nonnull
    public static Builder newDescription() {
        return new Builder();
    }

    @JsonProperty(value = "lang", required = true)
    @Nonnull
    public Locale lang() {
        return lang;
    }

    @JsonProperty(value = "value", required = true)
    @Nonnull
    public DescriptionValue value() {
        return value;
    }

    @JsonProperty("supportingMedia")
    @Nonnull
    public Optional<SupportingMedias> supportingMedia() {
        return supportingMedia;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<Locale> lang = Optional.empty();
        @Nonnull
        private Optional<DescriptionValue> value = Optional.empty();
        @Nonnull
        private Optional<SupportingMedias> supportingMedia = Optional.empty();

        @JsonProperty(value = "lang", required = true)
        @Nonnull
        public Builder withLang(@Nonnull Locale v) {
            this.lang = ofNonNull(v, "lang");
            return this;
        }

        @JsonProperty(value = "value", required = true)
        @Nonnull
        public Builder withValue(@Nonnull DescriptionValue v) {
            this.value = ofNonNull(v, "value");
            return this;
        }

        @JsonProperty("supportingMedia")
        @Nonnull
        public Builder withSupportingMedia(@Nullable SupportingMedias v) {
            this.supportingMedia = ofNullable(v);
            return this;
        }

        @Nonnull
        public Description build() {
            return new Description(this);
        }
    }
}
