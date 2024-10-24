package com.synaos.vulnerabilitiescache;

import javax.annotation.Nonnull;

import com.synaos.vulnerabilitiescache.packages.PackageRef;
import com.synaos.vulnerabilitiescache.packages.VersionedPackageRef;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Vulnerabilities {

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
