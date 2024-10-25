package com.github.synaos.vulnerabilitiescache.types;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.junit.jupiter.api.Test;

public class StringKindUnitTest {

    private static final ObjectMapper MAPPER = createMapper();

    @Test
    void serialize_success() throws Exception {
        final var instance = Foo.foo("ab\nc1");

        assertThat(MAPPER.writeValueAsString(instance))
            .isEqualTo("\"ab\\nc1\"");
    }

    @Test
    void deserialize_success() throws Exception {
        assertThat(MAPPER.readValue("\"abc\"", Foo.class))
            .isEqualTo(Foo.foo("abc"));
    }

    @Test
    void deserialize_fails_tooShort() {
        assertThatThrownBy(() -> MAPPER.readValue("\"ab\"", Foo.class))
            .isInstanceOf(ValueInstantiationException.class)
            .hasRootCauseInstanceOf(IllegalArgumentException.class)
            .hasRootCauseMessage("'ab' is expected to be at least 3 long; but is 2.");
    }

    @Test
    void deserialize_fails_tooLong() {
        assertThatThrownBy(() -> MAPPER.readValue("\"abcdefghijk\"", Foo.class))
            .isInstanceOf(ValueInstantiationException.class)
            .hasRootCauseInstanceOf(IllegalArgumentException.class)
            .hasRootCauseMessage("'abcdefghijk' is expected to be not longer than 10; but is 11.");
    }

    static class Foo extends StringKind<Foo> {

        @JsonCreator
        static Foo foo(@Nonnull String v) {
            return new Foo(v);
        }

        private Foo(@Nonnull String value) {
            super(3, 10, value);
        }

    }

    @Nonnull
    private static ObjectMapper createMapper() {
        return new ObjectMapper();
    }
}