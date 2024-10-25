package com.github.synaos.vulnerabilitiescache;

import javax.annotation.Nonnull;

import com.github.synaos.vulnerabilitiescache.packages.PackageRef;
import com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Vulnerabilities {

    @Nonnull
    default Flux<Vulnerability> findAllAndMap(@Nonnull Flux<PackageRef> refs) {
        return refs.flatMap(this::findBy);
    }

    @Nonnull
    default Flux<Vulnerability> findAndMap(@Nonnull Flux<VersionedPackageRef> refs) {
        return refs.flatMap(this::findBy);
    }

    @Nonnull
    Flux<Vulnerability> findBy(@Nonnull PackageRef ref);

    @Nonnull
    Flux<Vulnerability> findBy(@Nonnull VersionedPackageRef ref);

    @Nonnull
    default Mono<Boolean> has(@Nonnull PackageRef ref) {
        return findBy(ref).hasElements();
    }

    @Nonnull
    default Mono<Boolean> has(@Nonnull VersionedPackageRef ref) {
        return findBy(ref).hasElements();
    }

}
