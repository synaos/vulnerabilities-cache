package com.github.synaos.vulnerabilitiescache.cve;

import static com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE;
import static com.fasterxml.jackson.core.JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Throwables.throwSneaky;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.newBufferedReader;
import static java.time.ZoneOffset.UTC;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
        mapper = builder.mapper.copyWith(builder.mapper.getFactory().copy());
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
        } catch (JsonMappingException e) {
            return throwSneaky(new JsonMappingException((Closeable) e.getProcessor(), "Failed to read " + type.getSimpleName() + " from " + from + ".", e));
        } catch (IOException e) {
            return throwSneaky(e);
        }
    }

    public static final class Builder {

        @Nonnull
        private ObjectMapper mapper = newObjectMapper();

        @Nonnull
        private static ObjectMapper newObjectMapper() {
            return new ObjectMapper()
                .disable(AUTO_CLOSE_SOURCE)
                .enable(INCLUDE_SOURCE_IN_LOCATION)
                .findAndRegisterModules()
                .registerModule(new Module())
                ;
        }

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

    private static final class Module extends SimpleModule {

        private Module() {
            super("MyCustomModule", new com.fasterxml.jackson.core.Version(1, 0, 0, "runtime", null, null));
        }

        @Override
        public void setupModule(SetupContext context) {
            final var deserializers = new SimpleDeserializers();
            deserializers.addDeserializer(ZonedDateTime.class, new QuirkZonedDateTimeDeserializer());
            context.addDeserializers(deserializers);
        }

    }

    private static final class QuirkZonedDateTimeDeserializer extends FromStringDeserializer<ZonedDateTime> {

        private QuirkZonedDateTimeDeserializer() {
            super(ZonedDateTime.class);
        }

        @Override
        protected ZonedDateTime _deserialize(@Nonnull String value, @Nonnull DeserializationContext ctxt) throws IOException {
            try {
                return ZonedDateTime.parse(value);
            } catch (DateTimeParseException e) {
                try {
                    return LocalDateTime.parse(value).atZone(UTC);
                } catch (DateTimeParseException ignored) {
                    throw e;
                }
            }
        }
    }

}
