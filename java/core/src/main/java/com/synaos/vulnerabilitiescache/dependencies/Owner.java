package com.synaos.vulnerabilitiescache.dependencies;

import javax.annotation.Nonnull;

import com.synaos.vulnerabilitiescache.packages.VersionedPackageRef;

public interface Owner {

    @Nonnull
    VersionedPackageRef ref();

}
