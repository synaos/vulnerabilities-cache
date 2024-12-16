package com.github.synaos.vulnerabilitiescache.cve;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.RegexKind;

@ThreadSafe
@Immutable
public final class Tag extends RegexKind<Tag> {

    private static final Pattern pattern = Pattern.compile("^[a-z][a-z0-9]*(?:-[a-z][a-z0-9]*)*|x_.*$");

    @JsonCreator
    @Nonnull
    public static Tag tag(@Nonnull String v) {
        return new Tag(v);
    }

    private Tag(@Nonnull String value) {
        super(pattern, value);
    }

}
