package com.github.synaos.vulnerabilitiescache.types;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.junit.jupiter.api.Test;

public class ListKindUnitTest {

    private static final ObjectMapper MAPPER = createMapper();

    @Test
    void serialize_simple_success() throws Exception {
        final var instance = Bar.bar(Foo.foo(1), Foo.foo(2), Foo.foo(3));

        assertThat(MAPPER.writeValueAsString(instance))
            .isEqualTo("[{\"a\":\"a1\",\"b\":\"b1\"},{\"a\":\"a2\",\"b\":\"b2\"},{\"a\":\"a3\",\"b\":\"b3\"}]");
    }

    @Test
    void deserialize_simple_success() throws Exception {
        assertThat(MAPPER.readValue("[{\"a\":\"a1\",\"b\":\"b1\"},{\"a\":\"a2\",\"b\":\"b2\"},{\"a\":\"a3\",\"b\":\"b3\"}]", Bar.class))
            .isEqualTo(Bar.bar(Foo.foo(1), Foo.foo(2), Foo.foo(3)));
    }

    @Test
    void deserialize_simple_fails_tooShort() {
        assertThatThrownBy(() -> MAPPER.readValue("[{\"a\":\"a1\",\"b\":\"b1\"},{\"a\":\"a2\",\"b\":\"b2\"}]", Bar.class))
            .isInstanceOf(JsonMappingException.class)
            .hasRootCauseInstanceOf(IllegalArgumentException.class)
            .hasRootCauseMessage("[a1b1, a2b2] is expected to be at least 3 long; but is 2.");
    }

    @Test
    void deserialize_simple_fails_tooLong() {
        assertThatThrownBy(() -> MAPPER.readValue("[{\"a\":\"a1\",\"b\":\"b1\"},{\"a\":\"a2\",\"b\":\"b2\"},{\"a\":\"a3\",\"b\":\"b3\"},{\"a\":\"a4\",\"b\":\"b4\"},{\"a\":\"a5\",\"b\":\"b5\"},{\"a\":\"a6\",\"b\":\"b6\"}]", Bar.class))
            .isInstanceOf(JsonMappingException.class)
            .hasRootCauseInstanceOf(IllegalArgumentException.class)
            .hasRootCauseMessage("[a1b1, a2b2, a3b3, a4b4, a5b5, a6b6] is expected to be not longer than 5; but is 6.");
    }

    @JsonSerialize(using = Bar.Serializer.class)
    @JsonDeserialize(using = Bar.Deserializer.class)
    static class Bar extends ListKind<Foo, Bar> {

        @JsonCreator
        static Bar bar(@Nonnull Foo... vs) {
            return new Bar(List.of(vs));
        }

        private Bar(@Nonnull List<Foo> values) {
            super(3, 5, values);
        }

        static class Serializer extends ListKind.Serializer<Foo, Bar> {

            Serializer() {
                super(Foo.class);
            }

        }

        static class Deserializer extends ListKind.Deserializer<Foo, Bar> {

            Deserializer() {
                super(Foo.class, Bar::new);
            }

        }

    }

    record Foo(
        @JsonProperty("a")
        String a,
        @JsonProperty("b")
        String b
    ) {

        static Foo foo(int i) {
            return foo("a" + i, "b" + i);
        }

        static Foo foo(String a, String b) {
            return new Foo(a, b);
        }

        @Override
        public String toString() {
            return a + b;
        }
    }

    @Nonnull
    private static ObjectMapper createMapper() {
        return new ObjectMapper();
    }
}