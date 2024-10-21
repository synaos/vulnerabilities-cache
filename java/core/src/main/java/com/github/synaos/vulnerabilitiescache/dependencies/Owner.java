package com.github.synaos.vulnerabilitiescache.dependencies;

import javax.annotation.Nonnull;

import com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef;

public interface Owner {

    @Nonnull
    VersionedPackageRef ref();

}
