package com.github.synaos.vulnerabilitiescache.cve;

import java.net.URI;
import java.util.Optional;
import javax.annotation.Nonnull;

import com.github.synaos.vulnerabilitiescache.Id.Cpes;

public final class Product {
    @Nonnull
    private final Optional<VendorName> vendor;
    @Nonnull
    private final Optional<ProductName> product;
    @Nonnull
    private final Optional<URI> collectionURL;
    @Nonnull
    private final Optional<PackageName> packageName;
    @Nonnull
    private final Optional<Cpes> cpes;
    @Nonnull
    private final Optional<ModuleNames> modules;
    @Nonnull
    private final Optional<ProgramFiles> programFiles;
    @Nonnull
    private final Optional<ProgramRoutines> programRoutines;

}
