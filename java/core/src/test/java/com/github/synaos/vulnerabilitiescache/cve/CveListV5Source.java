package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Throwables.throwSneaky;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.synaos.vulnerabilitiescache.Id;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.util.StringUtils;

public final class CveListV5Source {

    public static final class Extension implements ParameterResolver, ExecutionCondition {

        @Nonnull
        private final CveListV5Source source = new CveListV5Source();

        @Override
        public boolean supportsParameter(
            @Nonnull ParameterContext parameterContext,
            @Nonnull ExtensionContext extensionContext
        ) throws ParameterResolutionException {
            return CveListV5Source.class.equals(parameterContext.getParameter().getType());
        }

        @Override
        @Nonnull
        public Object resolveParameter(
            @Nonnull ParameterContext parameterContext,
            @Nonnull ExtensionContext extensionContext
        ) throws ParameterResolutionException {
            return source;
        }

        @Override
        public ConditionEvaluationResult evaluateExecutionCondition(
            @Nonnull ExtensionContext context
        ) {
            return context.getElement()
                .filter(v -> v instanceof Method)
                .map(v -> (Method) v)
                .flatMap(method -> Stream.of(method.getParameters())
                    .filter(p -> p.getType().equals(CveListV5Source.class))
                    .findFirst()
                    .map(p -> {
                        if (source.isAvailable()) {
                            return ConditionEvaluationResult.enabled("cvelistV5 is available");
                        }
                        return ConditionEvaluationResult.disabled("cvelistV5 is unavailable");
                    }))
                .orElseGet(() -> ConditionEvaluationResult.enabled(null))
                ;
        }

    }

    @Nonnull
    private final Optional<Path> location;

    public CveListV5Source(@Nullable Path location) {
        this.location = ofNullable(location);
    }

    public CveListV5Source() {
        this(ofNullable(getProperty("cvelistV5.location", getenv("CVE_LIST_V5_LOCATION")))
            .filter(StringUtils::isNotBlank)
            .map(Paths::get)
            .orElse(null)
        );
    }

    public boolean isAvailable() {
        return locations().findAny().isPresent();
    }

    @Nonnull
    public Stream<Path> locations() {
        return location
            .map(v -> {
                    try {
                        return Files.walk(v);
                    } catch (IOException e) {
                        return throwSneaky(e);
                    }
                }
            )
            .orElseGet(Stream::empty)
            .filter(path -> {
                final var fn = path.getFileName().toString();
                if (!fn.endsWith(".json") || fn.length() < 6) {
                    return false;
                }
                return Id.Cve.tryOf(fn.substring(0, fn.length() - 5)).isPresent();
            });
    }

}
