package com.github.synaos.vulnerabilitiescache.dependencies;

import java.nio.file.Path;
import java.util.Optional;
import javax.annotation.Nonnull;

import com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef;

public interface Owner extends Dependencies {

    @Nonnull
    VersionedPackageRef ref();

    @Nonnull
    Optional<Path> path();

}
