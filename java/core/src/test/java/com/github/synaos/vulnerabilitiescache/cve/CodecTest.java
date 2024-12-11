package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.cve.Codec.defaultInstance;
import static java.nio.charset.StandardCharsets.UTF_8;
import static okhttp3.internal.Util.closeQuietly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;

@ExtendWith(CveListV5Source.Extension.class)
final class CodecTest {

    private final static Logger LOG = LoggerFactory.getLogger(CodecTest.class);

    @Nonnull
    private final Codec instance = defaultInstance();

    @TestFactory
    Stream<DynamicTest> readRecord() {
        return Stream.of(
            "CVE-1999-0001.json",
            "CVE-2021-1636.json",
            "CVE-2024-8023.json"
        ).map(name -> dynamicTest(name, () -> readRecord(name)));
    }

    private void readRecord(@Nonnull String name) throws IOException {
        try (final var reader = resourceReader(name)) {
            final var actual = instance.readRecord(reader);
            assertThat(actual).isNotNull();
        }
    }

    @Test
    void readAllRecordsIfPresent(@Nonnull CveListV5Source source) {
        final var count = new AtomicLong();
        source.locations()
            .parallel()
            .runOn(Schedulers.parallel())
            .doOnNext(location -> {
                final var actual = instance.readRecord(location);
                assertThat(actual).isNotNull();
                count.incrementAndGet();
            })
            .sequential()
            .blockLast();

        LOG.atInfo()
            .addKeyValue("amount", count.get())
            .log("all records where successfully read");
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