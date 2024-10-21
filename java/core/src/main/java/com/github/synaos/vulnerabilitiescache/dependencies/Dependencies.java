package com.github.synaos.vulnerabilitiescache.dependencies;

import javax.annotation.Nonnull;

import reactor.core.publisher.Flux;

public interface Dependencies {

    @Nonnull
    Flux<Dependency> dependencies();

}
