package com.github.synaos.vulnerabilitiescache.cvss.v3;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.RegexKind;

@ThreadSafe
@Immutable
public final class VectorString extends RegexKind<VectorString> {

    private final static Pattern pattern = Pattern.compile("^CVSS:3[.]0/((AV:[NALP]|AC:[LH]|PR:[UNLH]|UI:[NR]|S:[UC]|[CIA]:[NLH]|E:[XUPFH]|RL:[XOTWU]|RC:[XURC]|[CIA]R:[XLMH]|MAV:[XNALP]|MAC:[XLH]|MPR:[XUNLH]|MUI:[XNR]|MS:[XUC]|M[CIA]:[XNLH])/)*(AV:[NALP]|AC:[LH]|PR:[UNLH]|UI:[NR]|S:[UC]|[CIA]:[NLH]|E:[XUPFH]|RL:[XOTWU]|RC:[XURC]|[CIA]R:[XLMH]|MAV:[XNALP]|MAC:[XLH]|MPR:[XUNLH]|MUI:[XNR]|MS:[XUC]|M[CIA]:[XNLH])$");

    @JsonCreator
    @Nonnull
    public static VectorString vectorString(@Nonnull String v) {
        return new VectorString(v);
    }

    private VectorString(@Nonnull String value) {
        super(pattern, value);
    }

}
