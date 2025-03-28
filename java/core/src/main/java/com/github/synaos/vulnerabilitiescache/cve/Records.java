package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;

import javax.annotation.Nonnull;

import com.github.synaos.vulnerabilitiescache.Id;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Records {

    @Nonnull
    default Mono<Record> recordBy(@Nonnull String cveId) {
        requireNonNull(cveId, "cveId");
        return recordBy(Id.Cve.of(cveId));
    }

    @Nonnull
    default Mono<Record> recordBy(@Nonnull Id.Cve id) {
        requireNonNull(id, "id");
        return records()
            .filter(candidate -> id.equals(candidate.id()))
            .next();
    }

    @Nonnull
    Flux<Record> records();

}
