package com.github.synaos.vulnerabilitiescache.logging;

import static ch.qos.logback.classic.Level.*;
import static ch.qos.logback.core.pattern.color.ANSIConstants.*;
import static java.lang.Character.*;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isStatic;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Comparator.comparing;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.event.KeyValuePair;

public class TextLogLayout extends LayoutBase<ILoggingEvent> {

    @Nonnull
    public static final DateTimeFormatter defaultTimeStampFormatter = ofPattern("HH:mm:ss.SSS");

    @Nonnull
    public static ObjectMapper defaultObjectMapper() {
        return new ObjectMapper();
    }

    @Nonnull
    private DateTimeFormatter timeStampFormatter = defaultTimeStampFormatter;
    @Nonnull
    private ObjectMapper objectMapper = defaultObjectMapper();

    @Override
    public String doLayout(@Nonnull ILoggingEvent e) {
        final var sb = new StringBuilder();

        appendTimeStamp(e, sb);
        sb.append(' ');

        appendLevel(e, sb);
        sb.append(' ');

        final var msg = e.getFormattedMessage();
        if (msg != null) {
            appendMessage(msg, sb);
            sb.append(' ');
        }

        appendKeyValuesPairs(e, sb);

        appendThrowable(e, sb);

        appendCaller(e, sb);

        sb.append('\n');

        return sb.toString();
    }

    protected void appendTimeStamp(@Nonnull ILoggingEvent e, @Nonnull StringBuilder to) {
        append(Instant.ofEpochMilli(e.getTimeStamp()), to);
    }

    protected void appendLevel(@Nonnull ILoggingEvent e, @Nonnull StringBuilder to) {
        final var level = e.getLevel();
        append(format("%-5s", level.toString()), escapeCodeFor(level), to);
    }

    protected void appendMessage(@Nonnull String msg, @Nonnull StringBuilder to) {
        to.append(msg);
    }

    protected void appendKeyValuesPairs(@Nonnull ILoggingEvent e, @Nonnull StringBuilder to) {
        append(e.getKeyValuePairs(), escapeCodeFor(e), to);
    }

    protected void appendThrowable(@Nonnull ILoggingEvent e, @Nonnull StringBuilder to) {
        final var throwableProxy = e.getThrowableProxy();
        if (throwableProxy != null) {
            final var message = throwableProxy.getMessage();
            if (message != null) {
                append("throwable", escapeCodeFor(e), to);
                to
                    .append('=')
                    .append(formatSafe(throwableProxy.getClassName() + ": " + message))
                    .append(' ');
            }
        }
    }

    protected void appendCaller(@Nonnull ILoggingEvent e, @Nonnull StringBuilder to) {
        append("| " + formatCaller(e), WHITE_FG, to);
    }

    protected void append(@Nullable Collection<KeyValuePair> ins, @Nonnull String escapeCode, @Nonnull StringBuilder to) {
        if (ins == null || ins.isEmpty()) {
            return;
        }

        final var list = new ArrayList<>(ins);
        list.sort(comparing(kvp -> kvp.key));
        list.forEach(kvp -> {
            append(kvp.key, escapeCode, to);
            to
                .append('=')
                .append(formatSafe(kvp.value))
                .append(' ');
        });
    }

    protected void append(@Nonnull Instant instant, @Nonnull StringBuilder to) {
        append(ofInstant(instant, systemDefault()), to);
    }

    protected void append(@Nonnull LocalDateTime dateTime, @Nonnull StringBuilder to) {
        final var asString = getTimeStampFormatter().format(dateTime);
        append(asString, WHITE_FG, to);
    }

    protected void append(@Nonnull String what, @Nonnull String escapeCode, @Nonnull StringBuilder to) {
        to
            .append(ESC_START).append(escapeCode).append(ESC_END)
            .append(what)
            .append(ESC_START + RESET + DEFAULT_FG + ESC_END)
        ;
    }

    @Nonnull
    protected String formatSafe(@Nullable Object what) {
        if (what == null) {
            return "";
        }
        if (what instanceof CharSequence) {
            return formatSafe((CharSequence) what);
        }
        if (what instanceof Number || what instanceof Boolean) {
            return formatSafe(what.toString());
        }
        return tryFormatAsToStringOrAsJson(what);
    }

    @Nonnull
    protected String formatSafe(@Nullable CharSequence what) {
        if (what == null) {
            return "";
        }

        if (what.chars()
            .allMatch(c -> (isAlphabetic(c)
                || isDigit(c)
                || c == '.'
                || c == '-'
                || c == '_'
                || c == ':'
                || c == '/'
                || c == '\\')
                && !isWhitespace(c)
            )) {
            return what.toString();
        }

        return formatSafeAsJson(what);
    }

    @Nonnull
    protected String tryFormatAsToStringOrAsJson(@Nullable Object what) {
        if (what != null) {
            try {
                final var m = what.getClass().getMethod("toString");
                if (m.getReturnType().equals(String.class) && !isStatic(m.getModifiers()) && !m.getDeclaringClass().equals(Object.class)) {
                    return formatSafe(what.toString());
                }
            } catch (NoSuchMethodException | SecurityException ignored) {}
        }

        return formatSafeAsJson(what);
    }

    @Nonnull
    protected String formatSafeAsJson(@Nullable Object what) {
        try {
            return objectMapper.writeValueAsString(what);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    protected String formatCaller(@Nonnull ILoggingEvent e) {
        final var cda = e.getCallerData();
        if (cda != null && cda.length > 0) {
            final var cd = cda[0];
            return cd.getClassName() + '(' + cd.getFileName() + ':' + cd.getLineNumber() + ')';
        }
        return "?(?:?)";
    }

    @Nonnull
    protected String escapeCodeFor(@Nonnull ILoggingEvent e) {
        return escapeCodeFor(e.getLevel());
    }

    @Nonnull
    protected String escapeCodeFor(@Nonnull Level level) {
        return escapeCodeFor(level.toInt());
    }

    @Nonnull
    protected String escapeCodeFor(@Nonnegative int level) {
        if (level >= ERROR_INT) {
            return BOLD + RED_FG;
        }
        if (level >= WARN_INT) {
            return RED_FG;
        }
        if (level >= INFO_INT) {
            return BLUE_FG;
        }
        return DEFAULT_FG;
    }

    @Nonnull
    public String getTimeStampFormat() {
        return getTimeStampFormatter().toString();
    }

    public void setTimeStampFormat(@Nullable String pattern) {
        setTimeStampFormatter(pattern != null ? DateTimeFormatter.ofPattern(pattern) : defaultTimeStampFormatter);
    }

    @Nonnull
    public DateTimeFormatter getTimeStampFormatter() {
        return timeStampFormatter;
    }

    public void setTimeStampFormatter(@Nullable DateTimeFormatter formatter) {
        this.timeStampFormatter = formatter != null ? formatter : defaultTimeStampFormatter;
    }

    @Nonnull
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(@Nullable ObjectMapper objectMapper) {
        this.objectMapper = objectMapper != null ? objectMapper : defaultObjectMapper();
    }

}
