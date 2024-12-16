package com.github.synaos.vulnerabilitiescache.common;

import javax.annotation.Nullable;

public interface AutoCloseables {

    static void closeQuietly(@Nullable AutoCloseable v) {
        if (v != null) {
            try {
                v.close();
            } catch (Exception ignored) {}
        }
    }
}
