package com.github.synaos.vulnerabilitiescache.cve;

import java.util.Locale;
import java.util.Optional;
import javax.annotation.Nonnull;

public final class Description {
    @Nonnull
    private final Locale lang;
    @Nonnull
    private final String value;
    @Nonnull
    private final Optional<SupportingMedias> supportingMedia;

}
