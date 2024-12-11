package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

import java.net.URI;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Product.Builder.class)
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
    private final Optional<CpeNames> cpes;
    @Nonnull
    @JsonProperty("modules")
    private final Optional<ModuleNames> modules;
    @Nonnull
    @JsonProperty("programFiles")
    private final Optional<ProgramFiles> programFiles;
    @Nonnull
    @JsonProperty("programRoutines")
    private final Optional<ProgramRoutines> programRoutines;
    @JsonProperty("platforms")
    private final Optional<Platforms> platforms;
    @JsonProperty("repo")
    private final Optional<URI> repo;
    @JsonProperty("defaultStatus")
    private final Optional<Status> defaultStatus;
    @JsonProperty("versions")
    private final Optional<VersionRefs> versions;

    private Product(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.vendor = builder.vendor;
        this.product = builder.product;
        this.collectionURL = builder.collectionURL;
        this.packageName = builder.packageName;
        this.cpes = builder.cpes;
        this.modules = builder.modules;
        this.programFiles = builder.programFiles;
        this.programRoutines = builder.programRoutines;
        this.platforms = builder.platforms;
        this.repo = builder.repo;
        this.defaultStatus = builder.defaultStatus;
        this.versions = builder.versions;
    }

    @Nonnull
    public static Builder newProduct() {
        return new Builder();
    }

    @JsonProperty("vendor")
    @Nonnull
    public Optional<VendorName> vendor() {
        return vendor;
    }

    @JsonProperty("product")
    @Nonnull
    public Optional<ProductName> product() {
        return product;
    }

    @JsonProperty("collectionURL")
    @Nonnull
    public Optional<URI> collectionURL() {
        return collectionURL;
    }

    @JsonProperty("packageName")
    @Nonnull
    public Optional<PackageName> packageName() {
        return packageName;
    }

    @JsonProperty("cpes")
    @Nonnull
    public Optional<CpeNames> cpes() {
        return cpes;
    }

    @JsonProperty("modules")
    @Nonnull
    public Optional<ModuleNames> modules() {
        return modules;
    }

    @JsonProperty("programFiles")
    @Nonnull
    public Optional<ProgramFiles> programFiles() {
        return programFiles;
    }

    @JsonProperty("programRoutines")
    @Nonnull
    public Optional<ProgramRoutines> programRoutines() {
        return programRoutines;
    }

    @JsonProperty("platforms")
    @Nonnull
    public Optional<Platforms> platforms() {
        return platforms;
    }

    @JsonProperty("repo")
    @Nonnull
    public Optional<URI> repo() {
        return repo;
    }

    @JsonProperty("defaultStatus")
    @Nonnull
    public Optional<Status> defaultStatus() {
        return defaultStatus;
    }

    @JsonProperty("versions")
    @Nonnull
    public Optional<VersionRefs> versions() {
        return versions;
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties({"versionType"})
    public static final class Builder {
        @Nonnull
        private Optional<VendorName> vendor = Optional.empty();
        @Nonnull
        private Optional<ProductName> product = Optional.empty();
        @Nonnull
        private Optional<URI> collectionURL = Optional.empty();
        @Nonnull
        private Optional<PackageName> packageName = Optional.empty();
        @Nonnull
        private Optional<CpeNames> cpes = Optional.empty();
        @Nonnull
        private Optional<ModuleNames> modules = Optional.empty();
        @Nonnull
        private Optional<ProgramFiles> programFiles = Optional.empty();
        @Nonnull
        private Optional<ProgramRoutines> programRoutines = Optional.empty();
        @Nonnull
        private Optional<Platforms> platforms = Optional.empty();
        @Nonnull
        private Optional<URI> repo = Optional.empty();
        @Nonnull
        private Optional<Status> defaultStatus = Optional.empty();
        @Nonnull
        private Optional<VersionRefs> versions = Optional.empty();

        @JsonProperty("vendor")
        @Nonnull
        public Builder withVendor(@Nullable VendorName v) {
            this.vendor = ofNullable(v);
            return this;
        }

        @JsonProperty("product")
        @Nonnull
        public Builder withProduct(@Nullable ProductName v) {
            this.product = ofNullable(v);
            return this;
        }

        @JsonProperty("collectionURL")
        @Nonnull
        public Builder withCollectionURL(@Nullable URI v) {
            this.collectionURL = ofNullable(v);
            return this;
        }

        @JsonProperty("packageName")
        @Nonnull
        public Builder withPackageName(@Nullable PackageName v) {
            this.packageName = ofNullable(v);
            return this;
        }

        @JsonProperty("cpes")
        @Nonnull
        public Builder withCpes(@Nullable CpeNames v) {
            this.cpes = ofNullable(v);
            return this;
        }

        @JsonProperty("modules")
        @Nonnull
        public Builder withModules(@Nullable ModuleNames v) {
            this.modules = ofNullable(v);
            return this;
        }

        @JsonProperty("programFiles")
        @Nonnull
        public Builder withProgramFiles(@Nullable ProgramFiles v) {
            this.programFiles = ofNullable(v);
            return this;
        }

        @JsonProperty("programRoutines")
        @Nonnull
        public Builder withProgramRoutines(@Nullable ProgramRoutines v) {
            this.programRoutines = ofNullable(v);
            return this;
        }

        @JsonProperty("platforms")
        @Nonnull
        public Builder withPlatforms(@Nullable Platforms v) {
            this.platforms = ofNullable(v);
            return this;
        }

        @JsonProperty("repo")
        @Nonnull
        public Builder withRepo(@Nullable URI v) {
            this.repo = ofNullable(v);
            return this;
        }

        @JsonProperty("defaultStatus")
        @Nonnull
        public Builder withDefaultStatus(@Nullable Status v) {
            this.defaultStatus = ofNullable(v);
            return this;
        }

        @JsonProperty("versions")
        @Nonnull
        public Builder withVersions(@Nullable VersionRefs v) {
            this.versions = ofNullable(v);
            return this;
        }

        @Nonnull
        public Product build() {
            return new Product(this);
        }
    }
}
