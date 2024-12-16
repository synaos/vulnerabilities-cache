package com.github.synaos.vulnerabilitiescache.cve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;

@JsonDeserialize(builder = ProgramRoutine.Builder.class)
@ThreadSafe
@Immutable
public final class ProgramRoutine {

    @Nonnull
    @JsonProperty("name")
    private final ProgramName name;

    private ProgramRoutine(@Nonnull Builder builder) {
        requireNonNull(builder, "builder");
        this.name = requireToBePresent(builder.name, "name");
    }


    @Nonnull
    public static Builder newProgramRoutine() {
        return new Builder();
    }

    @JsonProperty(value = "name", required = true)
    @Nonnull
    public ProgramName name() {
        return name;
    }

    @JsonPOJOBuilder
    public static final class Builder {
        @Nonnull
        private Optional<ProgramName> name = Optional.empty();

        @JsonProperty(value = "name", required = true)
        @Nonnull
        public Builder withName(@Nonnull ProgramName v) {
            this.name = ofNonNull(v, "name");
            return this;
        }

        @Nonnull
        public ProgramRoutine build() {
            return new ProgramRoutine(this);
        }
    }
}
