package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.cve.Codec.defaultInstance;
import static java.nio.charset.StandardCharsets.UTF_8;
import static okhttp3.internal.Util.closeQuietly;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.annotation.Nonnull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CveListV5Source.Extension.class)
class CodecTest {

    @Nonnull
    private final Codec instance = defaultInstance();

    @Test
    void readRecord() throws IOException {
        try (final var reader = resourceReader("CVE-2024-8023.json")) {
            final var actual = instance.readRecord(reader);
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void readAllRecordsIfPresent(@Nonnull CveListV5Source source) {
        source.locations().forEach(location -> {
            final var actual = instance.readRecord(location);
            assertThat(actual).isNotNull();
        });
    }

    @Nonnull
    private Reader resourceReader(@Nonnull String file) {
        final var is = getClass().getResourceAsStream(file);
        if (is == null) {
            throw new IllegalArgumentException("Resource not found: " + file);
        }
        var success = false;
        try {
            final var result = new InputStreamReader(is, UTF_8);
            success = true;
            return result;
        } finally {
            if (!success) {
                closeQuietly(is);
            }
        }
    }
}