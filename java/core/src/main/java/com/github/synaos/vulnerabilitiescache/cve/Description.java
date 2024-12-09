package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Locale;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;

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

}
