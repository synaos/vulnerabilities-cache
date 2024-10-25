package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;


public final class VendorName extends StringKind<VendorName> {

    @JsonCreator
    @Nonnull
    public static VendorName vendorName(@Nonnull String v) {
        return new VendorName(v);
    }

    private VendorName(@Nonnull String value) {
        super(1, 512, value);
    }

}
