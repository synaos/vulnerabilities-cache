package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.net.URI;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = Reference.Builder.class)
@ThreadSafe
@Immutable
public final class Reference {
    @Nonnull
    @JsonProperty("url")
    private final URI url;
    @Nonnull
    @JsonProperty("name")
    private final Optional<ReferenceName> name;
    @Nonnull
    @JsonProperty
    private final Optional<Tags> tags;

    private Reference(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.url = requireToBePresent(builder.url, "url");
        this.name = builder.name;
        this.tags = builder.tags;
    }


    @Nonnull
    public static Builder newReference() {
        return new Builder();
    }

    @JsonProperty(value = "url", required = true)
    @Nonnull
    public URI url() {
        return url;
    }

    @JsonProperty("name")
    @Nonnull
    public Optional<ReferenceName> name() {
        return name;
    }

    @JsonProperty("tags")
    @Nonnull
    public Optional<Tags> tags() {
        return tags;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<URI> url = Optional.empty();
        @Nonnull
        private Optional<ReferenceName> name = Optional.empty();
        @Nonnull
        private Optional<Tags> tags = Optional.empty();

        @JsonProperty(value = "url", required = true)
        @Nonnull
        public Builder withUrl(@Nonnull URI v) {
            this.url = ofNonNull(v, "url");
            return this;
        }

        @JsonProperty("name")
        @Nonnull
        public Builder withName(@Nullable ReferenceName v) {
            this.name = ofNullable(v);
            return this;
        }

        @JsonProperty("tags")
        @Nonnull
        public Builder withTags(@Nullable Tags v) {
            this.tags = ofNullable(v);
            return this;
        }

        @Nonnull
        public Reference build() {
            return new Reference(this);
        }
    }
}
