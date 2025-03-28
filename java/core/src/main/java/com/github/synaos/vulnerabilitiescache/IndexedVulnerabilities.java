package com.github.synaos.vulnerabilitiescache;

import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;

import java.nio.file.Path;
import java.util.Optional;
import javax.annotation.Nonnull;

import com.github.synaos.vulnerabilitiescache.packages.PackageRef;
import com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef;
import javax.annotation.concurrent.ThreadSafe;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

@ThreadSafe
public class IndexedVulnerabilities implements Vulnerabilities {

    @Nonnull
    public static Builder newIndexedVulnerabilities() {
        return new Builder();
    }

    @Nonnull
    private final Path path;

    protected IndexedVulnerabilities(@Nonnull Builder builder) {
        path = requireToBePresent(builder.path, "path");
    }

    @NotNull
    @Override
    public Flux<Vulnerability> findBy(@NotNull PackageRef ref) {
        throw new UnsupportedOperationException("not implemented"); // TODO! Implement
    }

    @NotNull
    @Override
    public Flux<Vulnerability> findBy(@NotNull VersionedPackageRef ref) {
        throw new UnsupportedOperationException("not implemented"); // TODO! Implement
    }

    @Nonnull
    public Path path() {
        return path;
    }

    public static class Builder {

        @Nonnull
        private Optional<Path> path = Optional.empty();

        @Nonnull
        public Builder withPath(@Nonnull Path v) {
            this.path = ofNonNull(v, "path");
            return this;
        }

        @Nonnull
        public IndexedVulnerabilities build() {
            return new IndexedVulnerabilities(this);
        }

    }

}
