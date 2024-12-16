package com.github.synaos.vulnerabilitiescache.cvss.v2;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.RegexKind;

@ThreadSafe
@Immutable
public final class VectorString extends RegexKind<VectorString> {

    private final static Pattern pattern = Pattern.compile("^((AV:[NAL]|AC:[LMH]|Au:[MSN]|[CIA]:[NPC]|E:(U|POC|F|H|ND)|RL:(OF|TF|W|U|ND)|RC:(UC|UR|C|ND)|CDP:(N|L|LM|MH|H|ND)|TD:(N|L|M|H|ND)|[CIA]R:(L|M|H|ND))/)*(AV:[NAL]|AC:[LMH]|Au:[MSN]|[CIA]:[NPC]|E:(U|POC|F|H|ND)|RL:(OF|TF|W|U|ND)|RC:(UC|UR|C|ND)|CDP:(N|L|LM|MH|H|ND)|TD:(N|L|M|H|ND)|[CIA]R:(L|M|H|ND))$");

    @JsonCreator
    @Nonnull
    public static VectorString vectorString(@Nonnull String v) {
        return new VectorString(v);
    }

    private VectorString(@Nonnull String value) {
        super(pattern, value);
    }

}
