package com.github.synaos.vulnerabilitiescache.cve;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.RegexKind;

@ThreadSafe
@Immutable
public final class TagExtension extends RegexKind<TagExtension> {

    private static final String basePattern = "x_.*";
    private static final Pattern pattern = Pattern.compile("^" + basePattern + "$");

    @JsonCreator
    @Nonnull
    public static TagExtension tagExtension(@Nonnull String v) {
        return new TagExtension(v);
    }

    private TagExtension(@Nonnull String value) {
        super(2, 128, pattern, value);
    }

    public final static class Cna extends RegexKind<TagExtension> {

        @JsonCreator
        @Nonnull
        public static Cna cnaTagExtension(@Nonnull String v) {
            return new Cna(v);
        }

        private static final Pattern pattern = Pattern.compile("^" + basePattern + "|unsupported-when-assigned|exclusively-hosted-service|disputed$");

        private Cna(@Nonnull String value) {
            super(2, 128, pattern, value);
        }

    }

    public final static class Adp extends RegexKind<TagExtension> {

        @JsonCreator
        @Nonnull
        public static Adp adpTagExtension(@Nonnull String v) {
            return new Adp(v);
        }

        private static final Pattern pattern = Pattern.compile("^" + basePattern + "|disputed$");

        private Adp(@Nonnull String value) {
            super(2, 128, pattern, value);
        }

    }

}
