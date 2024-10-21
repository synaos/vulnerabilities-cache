package com.github.synaos.vulnerabilitiescache.dependencies;

import static com.github.synaos.vulnerabilitiescache.common.Objects.hash;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.synaos.vulnerabilitiescache.common.Objects;
import com.github.synaos.vulnerabilitiescache.packages.VersionedPackageRef;

public interface Dependency extends Comparable<Dependency> {

    @Nonnull
    VersionedPackageRef ref();

    @Nonnull
    Scope scope();

    @Nonnull
    default Optional<Owner> owner() {
        return Optional.empty();
    }

    @Override
    default int compareTo(@Nonnull Dependency o) {
        return ref().compareTo(o.ref());
    }

    enum Scope {
        project,
        production,
        testing,
        development,
        other,
    }

    static boolean equals(@Nonnull Dependency v, @Nullable Object o) {
        requireNonNull(v, "dependency");
        if (v == o) {return true;}
        if (o instanceof final Dependency other) {
            return Objects.equals(v.ref(), other.ref())
                && Objects.equals(v.scope(), other.scope());
        }
        return false;
    }

    static int hashCode(@Nonnull Dependency v) {
        requireNonNull(v, "dependency");
        return hash(v.ref(), v.scope());
    }

    static String toString(@Nonnull Dependency v) {
        requireNonNull(v, "dependency");
        return v.ref() + "/" + v.scope();
    }

}
