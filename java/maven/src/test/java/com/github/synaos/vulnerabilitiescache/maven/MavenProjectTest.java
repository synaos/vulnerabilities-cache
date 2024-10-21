package com.github.synaos.vulnerabilitiescache.maven;

import static com.github.synaos.vulnerabilitiescache.maven.MavenProject.newMavenProject;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MavenProjectTest {

    private static final Logger LOG = LoggerFactory.getLogger(MavenProjectTest.class);

    @Test
    void testMavenDependencies() {
        final var project = newMavenProject()
            .withPomXml(Paths.get("."))
            .build();

        final var dependencies = project.dependencies().collectList().block();
        LOG.atInfo()
            .addKeyValue("project", project)
            .addKeyValue("dependencies", dependencies)
            .log();
    }

    @Test
    void testCoreDependencies() {
        final var project = newMavenProject()
            .withPomXml(Paths.get("../core"))
            .build();

        final var dependencies = project.dependencies().collectList().block();
        LOG.atInfo()
            .addKeyValue("project", project)
            .addKeyValue("dependencies", dependencies)
            .log();
    }

    @Test
    void testUberPomMavenDependencies() {
        final var project = newMavenProject()
            .withPomXml(Paths.get(".."))
            .build();

        final var dependencies = project.dependencies().collectList().block();
        LOG.atInfo()
            .addKeyValue("project", project)
            .addKeyValue("dependencies", dependencies)
            .log();
    }


    @Test
    void testMavenModules() {
        final var projects = newMavenProject()
            .withPomXml(Paths.get("."), false)
            .buildRecursive()
            .sort()
            .collectList()
            .block();

        assertThat(projects).map(p -> p.ref().name()).containsExactly(
            "com.github.synaos.vulnerabilities-cache:maven"
        );
    }

    @Test
    void testUberPomModules() {
        final var projects = newMavenProject()
            .withPomXml(Paths.get(".."), false)
            .buildRecursive()
            .sort()
            .collectList()
            .block();

        assertThat(projects).map(p -> p.ref().name()).containsExactly(
            "com.github.synaos.vulnerabilities-cache:core",
            "com.github.synaos.vulnerabilities-cache:maven",
            "com.github.synaos.vulnerabilities-cache:vulnerabilities-cache"
        );
    }


}