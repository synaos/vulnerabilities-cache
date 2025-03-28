package com.github.synaos.vulnerabilitiescache.cve;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.RegexKind;

@ThreadSafe
@Immutable
public final class DataVersion extends RegexKind<DataVersion> {

    private static final Pattern pattern = Pattern.compile("^5\\.(0|[1-9][0-9]*)(\\.(0|[1-9][0-9]*))?$");

    @JsonCreator
    @Nonnull
    public static DataVersion dataVersion(@Nonnull String v) {
        return new DataVersion(v);
    }

    private DataVersion(@Nonnull String value) {
        super(pattern, value);
    }

}
