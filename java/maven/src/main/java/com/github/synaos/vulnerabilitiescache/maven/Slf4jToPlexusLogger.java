package com.github.synaos.vulnerabilitiescache.maven;

import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;

import java.util.function.Function;
import javax.annotation.Nonnull;

import org.codehaus.plexus.logging.Logger;

public class Slf4jToPlexusLogger implements Logger {

    @Nonnull
    private final org.slf4j.Logger logger;
    @Nonnull
    private final Function<String, Logger> loggerFactory;

    public Slf4jToPlexusLogger(@Nonnull org.slf4j.Logger logger, @Nonnull Function<String, Logger> loggerFactory) {
        this.logger = requireNonNull(logger, "logger");
        this.loggerFactory = requireNonNull(loggerFactory, "loggerFactory");
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void fatalError(String msg) {
        logger.error(msg);
    }

    @Override
    public void fatalError(String msg, Throwable t) {
        logger.error(msg, t);
    }

    @Override
    public boolean isFatalErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public Logger getChildLogger(String name) {
        return loggerFactory.apply(name);
    }

    @Override
    public int getThreshold() {
        if (isDebugEnabled()) {
            return Logger.LEVEL_DEBUG;
        }
        if (isInfoEnabled()) {
            return Logger.LEVEL_INFO;
        }
        if (isWarnEnabled()) {
            return Logger.LEVEL_WARN;
        }
        if (isErrorEnabled()) {
            return Logger.LEVEL_ERROR;
        }
        return Logger.LEVEL_FATAL;
    }

    @Override
    public String getName() {
        return logger.getName();
    }
}
