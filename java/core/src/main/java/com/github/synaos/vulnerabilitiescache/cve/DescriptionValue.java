package com.github.synaos.vulnerabilitiescache.cve;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.synaos.vulnerabilitiescache.types.StringKind;

@ThreadSafe
@Immutable
public final class DescriptionValue extends StringKind<DescriptionValue> {

    @JsonCreator
    @Nonnull
    public static DescriptionValue descriptionValue(@Nonnull String v) {
        return new DescriptionValue(v);
    }

    private DescriptionValue(@Nonnull String value) {
        super(1, 4096, value);
    }

}
