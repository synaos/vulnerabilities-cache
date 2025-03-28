package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.common.AutoCloseables.closeQuietly;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonEmpty;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.ofNonNull;
import static com.github.synaos.vulnerabilitiescache.common.Optionals.requireToBePresent;
import static com.github.synaos.vulnerabilitiescache.common.Throwables.throwSneaky;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.eclipse.jgit.lib.Constants.OBJ_BLOB;
import static reactor.core.Exceptions.AUTO_CLOSE;
import static reactor.core.scheduler.Schedulers.boundedElastic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.github.synaos.vulnerabilitiescache.Id;
import com.github.synaos.vulnerabilitiescache.Id.Cve;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.TreeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ThreadSafe
public class GitBasedRecords extends LocalRecords {

    private static final Logger LOG = LoggerFactory.getLogger(GitBasedRecords.class);

    public static final String defaultUri = "https://github.com/CVEProject/cvelistV5.git";
    public static final String defaultRef = "refs/heads/main";

    @Nonnull
    public static Builder newGitBasedRecords() {
        return new Builder();
    }

    @Nonnull
    private final String uri;
    @Nonnull
    private final String ref;
    @Nonnull
    private final ProgressMonitor progressMonitor;
    @Nonnull
    private final Duration cacheTimeout;
    @Nullable
    private LocalDateTime lastCacheUpdate = null;

    @Nullable
    private Git git = null;

    protected GitBasedRecords(@Nonnull Builder builder) throws IOException {
        super(
            requireToBePresent(builder.base, "base"),
            requireNonNull(builder.codec, "codec")
        );
        uri = requireNonEmpty(builder.uri, "uri");
        ref = requireNonEmpty(builder.ref, "ref");
        cacheTimeout = builder.cacheTimeout.orElseGet(() -> Duration.ofHours(-58));
        progressMonitor = requireNonNull(builder.progressMonitor, "progressMonitor");
    }

    @Nonnull
    public String uri() {
        return uri;
    }

    @Nonnull
    public String ref() {
        return ref;
    }

    @Nonnull
    protected ProgressMonitor progressMonitor() {
        return progressMonitor;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Nonnull
    protected Git openAndUpdateIfNeededOrCloneGit(@Nonnull Path root) {
        final var locks = instanceLock();
        final var readLock = locks.readLock();
        final var writeLock = locks.writeLock();
        var readLockUnlocked = false;
        readLock.lock();
        try {
            if (git != null) {
                if (isCacheStillUpdateToDate()) {
                    return git;
                }

                readLock.unlock();
                readLockUnlocked = true;

                writeLock.lock();
                try {
                    // Recheck...
                    if (isCacheStillUpdateToDate()) {
                        return git;
                    }

                    update(git);
                    return git;
                } finally {
                    writeLock.unlock();
                }
            }

            readLock.unlock();
            readLockUnlocked = true;

            writeLock.lock();
            try {
                return openAndUpdateGit(root);
            } catch (RepositoryNotFoundException e) {
                return cloneGit(root);
            } finally {
                writeLock.unlock();
            }
        } finally {
            if (!readLockUnlocked) {
                readLock.unlock();
            }
        }
    }

    @GuardedBy("instanceLock")
    protected boolean isCacheStillUpdateToDate() {
        return lastCacheUpdate != null
            && lastCacheUpdate.plus(cacheTimeout).isAfter(now());
    }

    @GuardedBy("instanceLock")
    protected void update(@Nonnull Git git) {
        try {
            git.fetch()
                .setProgressMonitor(progressMonitor())
                .call();
            lastCacheUpdate = now();
        } catch (GitAPIException e) {
            throwSneaky(e);
        }
    }

    @GuardedBy("instanceLock")
    @Nonnull
    protected Git openAndUpdateGit(@Nonnull Path root) throws RepositoryNotFoundException {
        if (git != null) {
            throw new IllegalStateException("git is already open");
        }

        var success = false;
        try {
            final var result = Git.open(root.toFile());
            try {
                update(result);
                git = result;
                success = true;
                return result;
            } finally {
                if (!success) {
                    closeQuietly(result);
                }
            }
        } catch (RepositoryNotFoundException e) {
            throw e;
        } catch (IOException e) {
            return throwSneaky(e);
        }
    }

    @GuardedBy("instanceLock")
    @Nonnull
    protected Git cloneGit(@Nonnull Path root) {
        if (git != null) {
            throw new IllegalStateException("git is already open");
        }

        var success = false;
        try {
            final var result = Git.cloneRepository()
                .setURI(uri())
                .setBranchesToClone(Set.of(ref()))
                .setProgressMonitor(progressMonitor())
                .setDepth(1)
                .setBare(true)
                .setDirectory(root.toFile())
                .call();
            try {

                lastCacheUpdate = now();
                git = result;
                success = true;
                return result;
            } finally {
                if (!success) {
                    closeQuietly(result);
                }
            }
        } catch (GitAPIException e) {
            return throwSneaky(e);
        }
    }

    @Nonnull
    protected Path repoBase() {
        return base()
            .resolve("repo");
    }

    @Nonnull
    protected Mono<Repository> repo() {
        return Mono.just(repoBase())
            .publishOn(boundedElastic())
            .map(this::openAndUpdateIfNeededOrCloneGit)
            .map(Git::getRepository)
            ;
    }

    @Nonnull
    protected String repoPathFor(@Nonnull Cve id) {
        requireNonNull(id, "id");
        return "cves"
            + "/" + yearPathPartOf(id)
            + "/" + subPathPartOf(id)
            + "/" + id + ".json";
    }

    @Override
    @Nonnull
    public Mono<Record> recordBy(@Nonnull Cve id) {
        requireNonNull(id, "id");
        return repo().transform(repoM -> Flux.defer(() -> {
                final var lock = instanceLock().readLock();
                lock.lock();
                try {
                    return repoM
                        .flatMap(repo -> {
                            try {
                                final var rev = repo.getFullBranch() + ":" + repoPathFor(id);
                                final var oid = repo.resolve(rev);
                                if (oid == null) {
                                    return Mono.empty();
                                }
                                return Mono.just(new RepoAndObjectId(repo, oid, rev));
                            } catch (IOException e) {
                                return throwSneaky(e);
                            }
                        })
                        .flatMap(RepoAndObjectId::objectLoader)
                        .map(this::readRecord);
                } finally {
                    lock.unlock();
                }
            })
        )
            ;
    }

    @Override
    @Nonnull
    public Flux<Record> records() {
        return repo().flux().transform(repo -> Flux.defer(() -> {
                final var lock = instanceLock().readLock();
                lock.lock();
                try {
                    return repo
                        .flatMap(this::walkRootObjectTree)
                        .flatMap(RepoAndObjectId::objectLoader)
                        .map(this::readRecord);
                } finally {
                    lock.unlock();
                }
            })
        );
    }

    protected record RepoAndObjectId(
        @Nonnull Repository repo,
        @Nonnull ObjectId oid,
        @Nonnull String plainObjectRev
    ) implements AutoCloseable {
        @Override
        public void close() {
            repo.close();
        }

        public Mono<ObjectLoaderAndPlainRev> objectLoader() {
            try {
                return Mono.just(new ObjectLoaderAndPlainRev(repo.open(oid, OBJ_BLOB), plainObjectRev));
            } catch (IncorrectObjectTypeException ignored) {
                return Mono.empty();
            } catch (IOException e) {
                return throwSneaky(e);
            }
        }
    }

    @Nonnull
    protected Flux<RepoAndObjectId> walkRootObjectTree(@Nonnull Repository of) {
        requireNonNull(of, "of");
        return rootObjectId(of)
            .flatMapMany(rootObjectId -> walkTree(rootObjectId, of));
    }

    @Nonnull
    protected Flux<RepoAndObjectId> walkTree(@Nonnull ObjectId oid, @Nonnull Repository of) {
        requireNonNull(oid, "oid");
        requireNonNull(of, "of");
        return Flux.using(
            () -> {
                final var treeWalk = new TreeWalk(of);
                treeWalk.setRecursive(true);
                treeWalk.setFilter(cveFileNameFilter);
                treeWalk.reset();
                treeWalk.addTree(oid);
                return treeWalk;
            },
            treeWalk -> Flux.generate(sink -> {
                try {
                    if (!treeWalk.next()) {
                        sink.complete();
                        return;
                    }
                } catch (IOException e) {
                    throwSneaky(e);
                }
                sink.next(new RepoAndObjectId(of, treeWalk.getObjectId(0), treeWalk.getPathString()));
            }),
            AUTO_CLOSE
        );
    }

    @Nonnull
    protected Mono<ObjectId> rootObjectId(@Nonnull Repository repo) {
        requireNonNull(repo, "repo");
        try {
            final var oid = repo.resolve(ref() + ":cves");
            return Mono.justOrEmpty(oid);
        } catch (IOException e) {
            return throwSneaky(e);
        }
    }

    protected record ObjectLoaderAndPlainRev(
        @Nonnull ObjectLoader objectLoader,
        @Nonnull String plainRev
    ) {}

    @Nonnull
    protected Record readRecord(@Nonnull ObjectLoaderAndPlainRev using) {
        requireNonNull(using, "using");
        try (final var os = using.objectLoader().openStream();
             final var reader = new InputStreamReader(os, UTF_8)
        ) {
            return codec().readRecord(reader, using.plainRev);
        } catch (IOException e) {
            return throwSneaky(e);
        }
    }

    @Override
    public void close() {
        try {
            final var lock = instanceLock().writeLock();
            lock.lock();
            try {
                final var git = this.git;
                if (git != null) {
                    try {
                        git.close();
                    } finally {
                        this.git = null;
                    }
                }
            } finally {
                lock.unlock();
            }
        } finally {
            super.close();
        }
    }

    public static final class Builder {
        @Nonnull
        private Optional<Path> base = Optional.empty();
        @Nonnull
        private String uri = defaultUri;
        @Nonnull
        private String ref = defaultRef;
        @Nonnull
        private Codec codec = Codec.defaultInstance();
        @Nonnull
        private ProgressMonitor progressMonitor = new LoggingProgressMonitor();
        @Nonnull
        private Optional<Duration> cacheTimeout = Optional.of(Duration.ofMinutes(10));

        @Nonnull
        public Builder withBase(@Nonnull Path v) {
            base = ofNonNull(v, "base");
            return this;
        }

        @Nonnull
        public Builder withUri(@Nonnull String v) {
            uri = requireNonEmpty(v, "uri");
            return this;
        }

        @Nonnull
        public Builder withRef(@Nonnull String v) {
            ref = requireNonEmpty(v, "ref");
            return this;
        }

        @Nonnull
        public Builder withCodec(@Nonnull Codec v) {
            codec = requireNonNull(v, "codec");
            return this;
        }

        @Nonnull
        public Builder withProgressMonitor(@Nullable ProgressMonitor v) {
            progressMonitor = ofNullable(v).orElse(NullProgressMonitor.INSTANCE);
            return this;
        }

        @Nonnull
        public Builder withLoggingProgressMonitor() {
            return withProgressMonitor(new LoggingProgressMonitor());
        }

        @Nonnull
        public Builder withCacheTimeout(@Nullable Duration v) {
            cacheTimeout = ofNullable(v)
                .filter(candidate -> !candidate.isNegative() && !candidate.isZero());
            return this;
        }

        @Nonnull
        public GitBasedRecords build() {
            try {
                return new GitBasedRecords(this);
            } catch (IOException e) {
                return throwSneaky(e);
            }
        }

    }

    protected static class LoggingProgressMonitor extends BatchingProgressMonitor {

        @Override
        protected void onUpdate(String taskName, int workCurr, Duration duration) {}

        @Override
        protected void onUpdate(String taskName, int workCurr, int workTotal, int percentDone, Duration duration) {}

        @Override
        protected void onEndTask(String taskName, int workCurr, Duration duration) {
            LOG.atInfo()
                .addKeyValue("task", taskName)
                .addKeyValue("duration", duration.truncatedTo(ChronoUnit.MILLIS))
                .log("git task finished");
        }

        @Override
        protected void onEndTask(String taskName, int workCurr, int workTotal, int percentDone, Duration duration) {
            onEndTask(taskName, workCurr, duration);
        }
    }

    @Nonnull
    protected static final TreeFilter cveFileNameFilter = new CveFileNameFilter();

    private static final class CveFileNameFilter extends TreeFilter {

        @Override
        public boolean include(TreeWalk walker) {
            if (walker.isSubtree()) {
                return true;
            }
            final var name = walker.getNameString();
            final var dot = name.indexOf('.');
            if (dot <= 0) {
                return false;
            }
            if (!".json".equalsIgnoreCase(name.substring(dot))) {
                return false;
            }
            return Id.Cve.tryOf(name.substring(0, dot)).isPresent();
        }

        @Override
        public int matchFilter(TreeWalk walker) throws IOException {
            if (walker.isSubtree()) {
                return -1;
            }
            return super.matchFilter(walker);
        }

        @Override
        public boolean shouldBeRecursive() {
            return true;
        }

        @Override
        public TreeFilter clone() {
            return this;
        }
    }

}
