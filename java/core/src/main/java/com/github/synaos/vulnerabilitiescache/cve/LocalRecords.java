package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.AutoCloseables.closeQuietly;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static com.github.synaos.vulnerabilitiescache.common.Throwables.throwSneaky;
import static java.nio.file.Files.*;
import static java.nio.file.StandardOpenOption.*;
import static reactor.core.publisher.Flux.fromStream;
import static reactor.core.scheduler.Schedulers.boundedElastic;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.github.synaos.vulnerabilitiescache.Id;
import com.github.synaos.vulnerabilitiescache.Id.Cve;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ThreadSafe
public class LocalRecords implements Records, AutoCloseable {

    @Nonnull
    public static Builder newLocalRecords() {
        return new Builder();
    }

    @Nonnull
    private final Path base;
    @Nonnull
    private final Codec codec;
    @Nonnull
    private final FileChannel fileChannel;
    @Nonnull
    private final FileLock fileLock;
    @Nonnull
    private final ReadWriteLock instanceLock = new ReentrantReadWriteLock();

    private LocalRecords(@Nonnull Builder builder) throws IOException {
        this(
            requireToBePresent(builder.base, "base"),
            builder.codec
        );
    }

    protected LocalRecords(
        @Nonnull Path base,
        @Nonnull Codec codec
    ) throws IOException {
        this.base = requireNonNull(base, "base");
        this.codec = requireNonNull(codec, "codec");

        createDirectories(base);

        var succes = false;
        fileChannel = FileChannel.open(lockFile(), READ, WRITE, CREATE);
        try {
            fileLock = fileChannel.lock();
            succes = true;
        } finally {
            if (!succes) {
                closeQuietly(fileChannel);
            }
        }
    }

    @Nonnull
    protected final Path base() {
        return base;
    }

    @Nonnull
    protected final Codec codec() {
        return codec;
    }

    @Nonnull
    protected Path lockFile() {
        return base().resolve("lock");
    }

    @Nonnull
    public ReadWriteLock instanceLock() {
        return instanceLock;
    }

    @Nonnull
    @Override
    public Mono<Record> recordBy(@Nonnull Cve id) {
        requireNonNull(id, "id");
        return Mono.just(id)
            .map(this::fileFor)
            .flatMap(v -> {
                final var lock = instanceLock().readLock();
                lock.lock();
                try {
                    return Mono.just(codec().readRecord(v));
                } catch (NoSuchElementException e) {
                    return Mono.empty();
                } finally {
                    lock.unlock();
                }
            });
    }

    @Nonnull
    @Override
    public Flux<Record> records() {
        final var lock = instanceLock().readLock();
        return Mono.just(base())
            .doOnNext(LocalRecords::assertIsDirectory)
            .doFirst(lock::lock)
            .publishOn(boundedElastic())
            .flatMapMany(LocalRecords::walk)
            .filter(this::accept)
            .map(codec()::readRecord)
            .doFinally(ignored -> lock.unlock());
    }

    private static void assertIsDirectory(@Nonnull Path candidate) {
        requireNonNull(candidate, "candidate");
        if (!exists(candidate)) {
            throw new IllegalStateException("Directory " + candidate + " does not exist.");
        }
        if (!isDirectory(candidate)) {
            throw new IllegalStateException(candidate + " exists but is not a directory.");
        }
    }

    @Nonnull
    private static Flux<Path> walk(@Nonnull Path through) {
        requireNonNull(through, "through");
        try {
            //noinspection BlockingMethodInNonBlockingContext
            return fromStream(Files.walk(through));
        } catch (IOException e) {
            return throwSneaky(e);
        }
    }

    protected boolean accept(@Nonnull Path candidate) {
        requireNonNull(candidate, "candidate");
        final var fn = candidate.getFileName().toString();
        if (!fn.endsWith(".json") || fn.length() < 6) {
            return false;
        }
        return Id.Cve.tryOf(fn.substring(0, fn.length() - 5)).isPresent();
    }

    @Nonnull
    protected Path fileFor(@Nonnull Cve id) {
        requireNonNull(id, "id");
        return base()
            .resolve(yearPathPartOf(id))
            .resolve(subPathPartOf(id))
            ;
    }

    @Nonnull
    protected String yearPathPartOf(@Nonnull Cve id) {
        requireNonNull(id, "id");
        return id.year().toString();
    }

    @Nonnull
    protected String subPathPartOf(@Nonnull Cve id) {
        requireNonNull(id, "id");
        return (id.sub() / 1000) + "xxx";
    }

    @Override
    public void close() {
        try {
            try {
                try {
                    fileLock.release();
                } finally {
                    fileLock.close();
                }
            } finally {
                fileChannel.close();
            }
        } catch (IOException e) {
            throwSneaky(e);
        }
    }

    public static final class Builder {
        @Nonnull
        private Optional<Path> base = Optional.empty();
        @Nonnull
        private Codec codec = Codec.defaultInstance();

        @Nonnull
        public Builder withBase(@Nonnull Path v) {
            base = ofNonNull(v, "base");
            return this;
        }

        @Nonnull
        public Builder withCodec(@Nonnull Codec v) {
            codec = requireNonNull(v, "codec");
            return this;
        }

        @Nonnull
        public LocalRecords build() {
            try {
                return new LocalRecords(this);
            } catch (IOException e) {
                return throwSneaky(e);
            }
        }

    }

}
