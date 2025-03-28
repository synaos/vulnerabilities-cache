package com.github.synaos.vulnerabilitiescache.cve;

import static com.github.synaos.vulnerabilitiescache.cve.GitBasedRecords.newGitBasedRecords;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import java.time.Year;
import java.util.Set;
import javax.annotation.Nonnull;

import com.github.synaos.vulnerabilitiescache.Id;
import com.github.synaos.vulnerabilitiescache.Id.Cve;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.io.TempDir;

class GitBasedRecordsExternalTest {

    private static final String testRepoUri = "https://github.com/githubtraining/example-bisect";
    private static final String testRepoRef = "refs/heads/master";
    private static final String testRepoRevision = "a9095e79eaafd9f11a9d12e5a1ae125fde81a5eb";
    private static final ProgressMonitor progressMonitor = new TextProgressMonitor();

    private static final Cve idCve1999_0001 = Cve.of("CVE-1999-0001");
    private static final Cve idCve2021_1636 = Cve.of("CVE-2021-1636");
    private static final Cve idCve2024_8023 = Cve.of("CVE-2024-8023");

    @SuppressWarnings({"EmptyTryBlock"})
    @Test
    void openAndUpdateGit(@TempDir Path root) throws Exception {
        try (final var instance = newInstance(root)) {
            final var repoRoot = root.resolve("repo");

            assertThatThrownBy(() -> instance.openAndUpdateGit(repoRoot))
                .isInstanceOf(RepositoryNotFoundException.class);

            try (final var ignored = Git.cloneRepository()
                .setURI(testRepoUri)
                .setBranchesToClone(Set.of(testRepoRef))
                .setDirectory(repoRoot.toFile())
                .setProgressMonitor(progressMonitor)
                .setBare(true)
                .call()
            ) {}

            final var actual = instance.openAndUpdateGit(repoRoot);
            assertThat(actual)
                .isNotNull();
            assertThat(actual.getRepository().getDirectory().getCanonicalFile())
                .isEqualTo(repoRoot.toFile().getCanonicalFile());
            assertThat(actual.getRepository().exactRef(testRepoRef).getObjectId().name())
                .isEqualTo(testRepoRevision);
        }
    }

    @Test
    void clone(@TempDir Path root) throws Exception {
        try (final var instance = newInstance(root)) {
            final var repoRoot = root.resolve("repo");

            final var actual = instance.cloneGit(repoRoot);
            assertThat(actual)
                .isNotNull();
            assertThat(actual.getRepository().getDirectory().getCanonicalFile())
                .isEqualTo(repoRoot.toFile().getCanonicalFile());
            assertThat(actual.getRepository().exactRef(testRepoRef).getObjectId().name())
                .isEqualTo(testRepoRevision);
        }
    }

    @Test
    @EnabledIfEnvironmentVariable(
        named = "TESTS_WITH_FULL_CVE_REPO",
        matches = "YES|ON|TRUE|1|ENABLED|yes|on|true|1|enabled",
        disabledReason = "Should be only enabled when testing locally, but never for regular tests because it produces a lot of IO. To enable set environment variable TESTS_WITH_FULL_CVE_REPO=true"
    )
    void roundTripWithLiveRepo(@TempDir Path root) {
        try (final var instance = newGitBasedRecords()
            .withBase(root)
            .withProgressMonitor(progressMonitor)
            .build()
        ) {
            {
                final var actual = instance.recordBy(idCve1999_0001).block();
                assertThat(actual).isNotNull();
                assertThat(actual.cveMetadata().cveId()).isEqualTo(idCve1999_0001);
            }
            {
                final var actual = instance.recordBy(idCve2021_1636).block();
                assertThat(actual).isNotNull();
                assertThat(actual.cveMetadata().cveId()).isEqualTo(idCve2021_1636);
            }
            {
                final var actual = instance.recordBy(idCve2024_8023).block();
                assertThat(actual).isNotNull();
                assertThat(actual.cveMetadata().cveId()).isEqualTo(idCve2024_8023);
            }
            {
                final var actual = instance.recordBy("CVE-1999-6666").blockOptional();
                assertThat(actual).isEmpty();
            }
            {
                final var year1999 = Year.of(1999);
                final var year2000 = Year.of(2000);
                final var items = instance.records()
                    // This test really assumes that all first CVEs of the year 1999 are really at the beginning
                    // of the repository. Query all might be to memory intensive and might tage too long.
                    .take(10_000)
                    .collectSortedList()
                    .block();
                assertThat(items).isNotEmpty();
                for (var i = 0; i < 980; i++) {
                    assertThat(items).element(i).extracting(Record::id).extracting(Id.Cve::year).isEqualTo(year1999);
                }
                for (var i = 1_579; i < 2_569; i++) {
                    assertThat(items).element(i).extracting(Record::id).extracting(Id.Cve::year).isEqualTo(year2000);
                }
            }
            {
                final var items = instance.records()
                    .filter(record -> idCve2024_8023.equals(record.id()))
                    .collectSortedList()
                    .block();
                assertThat(items).isNotEmpty();
                assertThat(items).hasSize(1);
                assertThat(items).hasSize(1);
                assertThat(items).element(0).extracting(Record::id).isEqualTo(idCve2024_8023);
            }
        }
    }

    @Nonnull
    private static GitBasedRecords newInstance(@Nonnull Path base) {
        return newGitBasedRecords()
            .withBase(base)
            .withUri(testRepoUri)
            .withRef(testRepoRef)
            .withProgressMonitor(progressMonitor)
            .build();
    }
}