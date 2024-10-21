package com.github.synaos.vulnerabilitiescache.maven;

import org.codehaus.plexus.logging.BaseLoggerManager;
import org.codehaus.plexus.logging.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jToPlexusLoggerManager extends BaseLoggerManager {

    @Override
    protected Logger createLogger(String key) {
        return new Slf4jToPlexusLogger(LoggerFactory.getLogger(key), this::createLogger);
    }

}
