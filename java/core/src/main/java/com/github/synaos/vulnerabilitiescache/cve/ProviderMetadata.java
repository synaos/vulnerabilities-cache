package com.github.synaos.vulnerabilitiescache.cve;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

public final class ProviderMetadata {

    @Nonnull
    private final UUID orgId;
    @Nonnull
    private final Optional<String> shortName;
    @Nonnull
    private final Optional<ZonedDateTime> dateUpdated;


}
