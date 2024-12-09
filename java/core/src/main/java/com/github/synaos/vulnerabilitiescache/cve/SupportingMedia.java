package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
