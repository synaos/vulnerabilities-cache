package com.github.synaos.vulnerabilitiescache.cve;

import java.net.URI;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.synaos.vulnerabilitiescache.Id.Cpes;

@ThreadSafe
@Immutable
public final class Product {
    @Nonnull
    @JsonProperty("vendor")
    private final Optional<VendorName> vendor;
    @Nonnull
    @JsonProperty("product")
    private final Optional<ProductName> product;
    @Nonnull
    @JsonProperty("collectionURL")
    private final Optional<URI> collectionURL;
    @Nonnull
    @JsonProperty("packageName")
    private final Optional<PackageName> packageName;
    @Nonnull
    @JsonProperty("cpes")
    private final Optional<Cpes> cpes;
    @Nonnull
    @JsonProperty("modules")
    private final Optional<ModuleNames> modules;
    @Nonnull
    @JsonProperty("programFiles")
    private final Optional<ProgramFiles> programFiles;
    @Nonnull
    @JsonProperty("programRoutines")
    private final Optional<ProgramRoutines> programRoutines;

}
