package com.github.synaos.vulnerabilitiescache.cve;

import java.net.URI;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
