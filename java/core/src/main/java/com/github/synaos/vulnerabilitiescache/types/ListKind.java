package com.github.synaos.vulnerabilitiescache.types;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNegative;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

@ThreadSafe
@Immutable
public abstract class ListKind<V, T extends ListKind<V, T>> implements Iterable<V> {

    @Nonnegative
    private final Optional<Integer> minLength;
    @Nonnegative
    private final Optional<Integer> maxLength;
    @Nonnull
    private final List<V> entries;

    protected ListKind(@Nonnull List<V> entries) {
        this(null, null, requireNonNull(entries, "entries"));
    }

    protected ListKind(
        @Nullable @Nonnegative Integer minLength,
        @Nullable @Nonnegative Integer maxLength,
        @Nonnull List<V> entries
    ) {
        this.minLength = ofNullable(minLength).map(v -> requireNonNegative(v, "minLength"));
        this.maxLength = ofNullable(maxLength).map(v -> requireNonNegative(v, "maxLength"));
        this.entries = List.copyOf(requireNonNull(entries, "entries"));

        this.minLength.ifPresent(v -> {
            if (this.entries.size() < v) {
                throw new IllegalArgumentException(format("%s is expected to be at least %d long; but is %d.", this.entries, v, this.entries.size()));
            }
        });
        this.maxLength.ifPresent(v -> {
            if (this.entries.size() > v) {
                throw new IllegalArgumentException(format("%s is expected to be not longer than %d; but is %d.", this.entries, v, this.entries.size()));
            }
        });
    }

    @Nonnull
    public final List<V> entries() {
        return entries;
    }

    public final int size() {
        return entries.size();
    }

    public final boolean isEmpty() {
        return entries.isEmpty();
    }

    public final boolean hasContent() {
        return !isEmpty();
    }

    @Nonnull
    @Override
    public final Iterator<V> iterator() {
        return entries.iterator();
    }

    @Nonnull
    @Nonnegative
    protected final Optional<Integer> minLength() {
        return minLength;
    }

    @Nonnull
    @Nonnegative
    protected final Optional<Integer> maxLength() {
        return maxLength;
    }

    @Override
    public final String toString() {
        return entries.toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || !getClass().equals(o.getClass())) {return false;}
        final var that = (ListKind<?, ?>) o;
        return Objects.equals(minLength, that.minLength)
            && Objects.equals(maxLength, that.maxLength)
            && Objects.equals(entries, that.entries);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(minLength, maxLength, entries);
    }

    public static class Deserializer<V, T extends ListKind<V, T>> extends ContainerDeserializerBase<T> implements ContextualDeserializer {

        private static final TypeFactory typeFactory = TypeFactory.defaultInstance();

        @Nonnull
        private final Function<List<V>, T> factory;
        @Nonnull
        private final JavaType valueType;
        @Nonnull
        private final Optional<JsonDeserializer<V>> valueDeserializer;

        protected Deserializer(
            @Nonnull JavaType valueType,
            @Nonnull Function<List<V>, T> factory,
            @Nullable JsonDeserializer<V> valueDeserializer
        ) {
            super(typeFactory.constructCollectionLikeType(List.class, requireNonNull(valueType, "valueType")));
            this.factory = requireNonNull(factory, "factory");
            this.valueType = valueType;
            this.valueDeserializer = ofNullable(valueDeserializer);
        }

        protected Deserializer(
            @Nonnull JavaType valueType,
            @Nonnull Function<List<V>, T> factory
        ) {
            this(
                requireNonNull(valueType, "valueType"),
                requireNonNull(factory, "factory"),
                null
            );
        }

        protected Deserializer(
            @Nonnull Class<V> valueType,
            @Nonnull Function<List<V>, T> factory
        ) {
            this(typeFactory.constructType(requireNonNull(valueType, "valueType")),
                requireNonNull(factory, "factory")
            );
        }

        @Override
        public JsonDeserializer<Object> getContentDeserializer() {
            //noinspection unchecked
            return (JsonDeserializer<Object>) valueDeserializer();
        }

        @Nonnull
        public JsonDeserializer<V> valueDeserializer() {
            return valueDeserializer.orElseThrow(() -> new IllegalStateException("createContextual() not yet called"));
        }

        @Override
        public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            final var deserializer = ctxt.findContextualValueDeserializer(valueType, property);
            //noinspection unchecked
            return new Deserializer<>(valueType, factory, (JsonDeserializer<V>) deserializer);
        }

        @Override
        public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            switch (p.currentToken()) {
                case VALUE_NULL:
                    return null;
                case START_ARRAY:
                    break;
                default:
                    //noinspection unchecked
                    return (T) ctxt.handleUnexpectedToken(getContentType(), p);
            }
            final var buf = new ArrayList<V>();
            try {
                while (true) {
                    if (p.nextToken() == END_ARRAY) {
                        final var result = factory.apply(List.copyOf(buf));
                        return requireNonNull(result, "result");
                    }
                    buf.add(valueDeserializer().deserialize(p, ctxt));
                }
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath(e, buf, buf.size());
            }
        }
    }

    public static class Serializer<V, T extends ListKind<V, T>> extends ContainerSerializer<T> implements ContextualSerializer {

        private static final TypeFactory typeFactory = TypeFactory.defaultInstance();

        @Nonnull
        private final JavaType valueType;
        @Nonnull
        private final Optional<JsonSerializer<V>> valueSerializer;

        protected Serializer(
            @Nonnull JavaType valueType,
            @Nullable JsonSerializer<V> valueSerializer
        ) {
            super(typeFactory.constructCollectionLikeType(List.class, requireNonNull(valueType, "valueType")));
            this.valueType = valueType;
            this.valueSerializer = ofNullable(valueSerializer);
        }

        protected Serializer(@Nonnull JavaType valueType) {
            this(requireNonNull(valueType, "valueType"), null);
        }

        protected Serializer(@Nonnull Class<V> valueType) {
            this(typeFactory.constructType(requireNonNull(valueType, "valueType")));
        }

        @Override
        public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
            final var serializer = prov.findValueSerializer(getContentType(), property);
            //noinspection unchecked
            return new Serializer<V, T>(valueType, (JsonSerializer<V>) serializer);
        }

        @Override
        public JavaType getContentType() {
            return valueType;
        }

        @Override
        public JsonSerializer<?> getContentSerializer() {
            return valueSerializer();
        }

        public JsonSerializer<V> valueSerializer() {
            return valueSerializer.orElseThrow(() -> new IllegalStateException("createContextual() not yet called"));
        }

        @Override
        public boolean hasSingleElement(T value) {
            return value != null && value.size() == 1;
        }

        @Override
        protected ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
            return this;
        }

        @Override
        public void serialize(T values, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (values == null) {
                gen.writeNull();
                return;
            }

            gen.writeStartArray(values, values.size());
            for (final var value : values) {
                valueSerializer().serialize(value, gen, provider);
            }
            gen.writeEndArray();
        }
    }

}
