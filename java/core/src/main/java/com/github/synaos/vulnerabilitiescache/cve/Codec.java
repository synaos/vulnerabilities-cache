package com.github.synaos.vulnerabilitiescache.cve;

import static com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE;
import static com.fasterxml.jackson.core.JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Throwables.throwSneaky;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.newBufferedReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.ObjectMapper;

@ThreadSafe
@Immutable
public final class Codec {

    @Nonnull
    private static final Codec defaultInstance = newCodec().build();

    @Nonnull
    public static Codec defaultInstance() {
        return defaultInstance;
    }

    @Nonnull
    public static Builder newCodec() {
        return new Builder();
    }

    @Nonnull
    private final ObjectMapper mapper;

    private Codec(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        mapper = builder.mapper.copyWith(builder.mapper.getFactory().copy())
            .findAndRegisterModules();
    }

    @Nonnull
    public Record readRecord(@Nonnull Reader from) {
        return read(Record.class, from);
    }

    @Nonnull
    public Record readRecord(@Nonnull Path from) {
        return read(Record.class, from);
    }

    @Nonnull
    private <T> T read(@Nonnull Class<T> type, @Nonnull Reader from) {
        try {
            return mapper.readValue(from, type);
        } catch (IOException e) {
            return throwSneaky(e);
        }
    }

    @Nonnull
    private <T> T read(@Nonnull Class<T> type, @Nonnull Path from) {
        try (final var reader = newBufferedReader(from, UTF_8)) {
            return read(type, reader);
        } catch (IOException e) {
            return throwSneaky(e);
        }
    }

    public static final class Builder {

        @Nonnull
        private ObjectMapper mapper = new ObjectMapper()
            .disable(AUTO_CLOSE_SOURCE)
            .enable(INCLUDE_SOURCE_IN_LOCATION);

        @Nonnull
        public Builder withMapper(@Nonnull ObjectMapper v) {
            mapper = requireNonNull(v, "objectMapper");
            return this;
        }

        @Nonnull
        public Codec build() {
            return new Codec(this);
        }

    }

}
