package com.github.synaos.vulnerabilitiescache.cvss.v4;

import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.RegexKind;

@ThreadSafe
@Immutable
public final class VectorString extends RegexKind<VectorString> {

    private final static Pattern pattern = Pattern.compile("^CVSS:4[.]0/AV:[NALP]/AC:[LH]/AT:[NP]/PR:[NLH]/UI:[NPA]/VC:[HLN]/VI:[HLN]/VA:[HLN]/SC:[HLN]/SI:[HLN]/SA:[HLN](/E:[XAPU])?(/CR:[XHML])?(/IR:[XHML])?(/AR:[XHML])?(/MAV:[XNALP])?(/MAC:[XLH])?(/MAT:[XNP])?(/MPR:[XNLH])?(/MUI:[XNPA])?(/MVC:[XNLH])?(/MVI:[XNLH])?(/MVA:[XNLH])?(/MSC:[XNLH])?(/MSI:[XNLHS])?(/MSA:[XNLHS])?(/S:[XNP])?(/AU:[XNY])?(/R:[XAUI])?(/V:[XDC])?(/RE:[XLMH])?(/U:(X|Clear|Green|Amber|Red))?$");

    @JsonCreator
    @Nonnull
    public static VectorString vectorString(@Nonnull String v) {
        return new VectorString(v);
    }

    private VectorString(@Nonnull String value) {
        super(pattern, value);
    }

}
