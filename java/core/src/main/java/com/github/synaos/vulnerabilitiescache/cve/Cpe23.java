package com.github.synaos.vulnerabilitiescache.cve;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.RegexKind;

@ThreadSafe
@Immutable
public final class Cpe23 extends RegexKind<Cpe23> {

    private static final Pattern pattern = Pattern.compile("(cpe:2\\.3:[aho*\\-](:(((\\?*|\\*?)([a-zA-Z0-9\\-._]|(\\\\[\\\\*?!\"#$%&'()+,/:;<=>@\\[\\]\\^`{|}~]))+(\\?*|\\*?))|[*\\-])){5}(:(([a-zA-Z]{2,3}(-([a-zA-Z]{2}|[0-9]{3}))?)|[*\\-]))(:(((\\?*|\\*?)([a-zA-Z0-9\\-._]|(\\\\[\\\\*?!\"#$%&'()+,/:;<=>@\\[\\]\\^`{|}~]))+(\\?*|\\*?))|[*\\-])){4})");

    @JsonCreator
    @Nonnull
    public static Cpe23 cpe23(@Nonnull String v) {
        return new Cpe23(v);
    }

    private Cpe23(@Nonnull String value) {
        super(1, 2048, pattern, value);
    }

}
