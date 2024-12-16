package com.github.synaos.vulnerabilitiescache;

import static com.github.synaos.vulnerabilitiescache.Id.Type.*;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNegative;
import static com.github.synaos.vulnerabilitiescache.common.Objects.requireNonNull;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Locale.US;

import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.synaos.vulnerabilitiescache.types.ListKind;

public interface Id extends Comparable<Id> {

    @Nonnull
    @JsonCreator
    static Id of(@Nonnull String name) {
        requireNonNull(name, "name");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Illegal vulnerability id: <empty>");
        }
        if (name.startsWith("CVE-")) {
            return Cve.of(name);
        }
        if (name.toLowerCase(US).startsWith("cpe:")) {
            return Cpe.of(name);
        }
        if (name.startsWith("CWE-")) {
            return Cwe.of(name);
        }
        if (name.startsWith("CAPEC-")) {
            return Capec.of(name);
        }
        throw new IllegalArgumentException("Illegal vulnerability id: " + name);
    }

    @Nonnull
    @JsonValue
    String name();

    @Nonnull
    Type type();

    @Override
    default int compareTo(@Nonnull Id o) {
        var difference = name().compareTo(o.name());
        if (difference > 0) {
            return 1;
        }
        if (difference < 0) {
            return -1;
        }

        difference = type().compareTo(o.type());
        return Integer.compare(difference, 0);
    }

    abstract class Base implements Id {

        @Override
        public String toString() {
            return name();
        }

    }

    @JsonSerialize(using = Cves.Serializer.class)
    @JsonDeserialize(using = Cves.Deserializer.class)
    final class Cves extends ListKind<Cve, Cves> {

        public Cves(@Nonnull List<Cve> entries) {
            super(1, null, entries);
        }

        static class Serializer extends ListKind.Serializer<Cve, Cves> {
            Serializer() {
                super(Cve.class);
            }
        }

        static class Deserializer extends ListKind.Deserializer<Cve, Cves> {
            Deserializer() {
                super(Cve.class, Cves::new);
            }
        }
    }

    final class Cve extends Base {

        private final static Pattern pattern = Pattern.compile("CVE-([0-9]{4})-([0-9]{4,19})");

        @Nonnull
        private final Year year;
        @Nonnegative
        private final long sub;

        @Nonnull
        @JsonCreator
        public static Cve of(@Nonnull String name) {
            requireNonNull(name, "name");
            return tryOf(name)
                .orElseThrow(() -> new IllegalArgumentException("Illegal CVE: " + (name.isEmpty() ? "<empty>" : name)));
        }

        @Nonnull
        public static Optional<Cve> tryOf(@Nonnull String name) {
            requireNonNull(name, "name");
            if (name.isEmpty()) {
                return Optional.empty();
            }
            final var matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                return Optional.empty();
            }
            final var yearI = parseInt(matcher.group(1));
            final var sub = parseLong(matcher.group(2));
            return Optional.of(new Cve(Year.of(yearI), sub));
        }

        private Cve(@Nonnull Year year, long sub) {
            this.year = requireNonNull(year, "year");
            this.sub = requireNonNegative(sub, "sub");
        }

        @Nonnull
        public Year year() {
            return year;
        }

        @Nonnegative
        public long sub() {
            return sub;
        }

        @Nonnull
        public String subString() {
            if (sub < 10) {
                return "000" + sub;
            }
            if (sub < 100) {
                return "00" + sub;
            }
            if (sub < 1000) {
                return "0" + sub;
            }
            return "" + sub;
        }

        @Nonnull
        @Override
        public String name() {
            return "CVE-" + year() + "-" + subString();
        }

        @Nonnull
        @Override
        public Type type() {
            return cve;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof final Cve cve)) {return false;}
            return sub == cve.sub && Objects.equals(year, cve.year);
        }

        @Override
        public int hashCode() {
            return Objects.hash(year, sub);
        }
    }

    final class Cpe extends Base {

        private final static Pattern pattern = Pattern.compile("([c][pP][eE]:/[AHOaho]?(:[A-Za-z0-9._\\-~%]*){0,6})|(cpe:2\\.3:[aho*\\-](:(((\\?*|\\*?)([a-zA-Z0-9\\-._]|(\\\\[\\\\*?!\"#$%&'()+,/:;<=>@\\[\\]^`{|}~]))+(\\?*|\\*?))|[*\\-])){5}(:(([a-zA-Z]{2,3}(-([a-zA-Z]{2}|[0-9]{3}))?)|[*\\-]))(:(((\\?*|\\*?)([a-zA-Z0-9\\-._]|(\\\\[\\\\*?!\"#$%&'()+,/:;<=>@\\[\\]^`{|}~]))+(\\?*|\\*?))|[*\\-])){4})");

        @Nonnull
        private final String value;

        @Nonnull
        @JsonCreator
        public static Cpe of(@Nonnull String name) {
            requireNonNull(name, "name");
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Illegal CPE: <empty>");
            }
            final var matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Illegal CPE: " + name);
            }
            return new Cpe(matcher.group());
        }

        private Cpe(@Nonnull String value) {
            this.value = requireNonNull(value, "value");
        }

        @Nonnull
        @Override
        @JsonValue
        public String name() {
            return this.value;
        }

        @Nonnull
        @Override
        public Type type() {
            return cpe;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof final Cpe cpe)) {return false;}
            return Objects.equals(value, cpe.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }

    final class Cwe extends Base {

        private final static Pattern pattern = Pattern.compile("CWE-([1-9][0-9]{0,5})");

        @Nonnegative
        private final long number;

        @Nonnull
        @JsonCreator
        public static Cwe of(@Nonnull String name) {
            requireNonNull(name, "name");
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Illegal CWE: <empty>");
            }
            final var matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Illegal CWE: " + name);
            }
            final var number = parseLong(matcher.group(1));
            return new Cwe(number);
        }

        private Cwe(@Nonnegative long number) {
            this.number = requireNonNegative(number, "number");
        }

        @Nonnegative
        public long number() {
            return number;
        }

        @Nonnull
        @Override
        public String name() {
            return "CWE-" + number();
        }

        @Nonnull
        @Override
        public Type type() {
            return cwe;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof final Cwe cwe)) {return false;}
            return number == cwe.number;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(number);
        }
    }

    final class Capec extends Base {

        private final static Pattern pattern = Pattern.compile("CAPEC-([1-9][0-9]{0,4})");

        @Nonnegative
        private final long number;

        @Nonnull
        @JsonCreator
        public static Capec of(@Nonnull String name) {
            requireNonNull(name, "name");
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Illegal CAPEC: <empty>");
            }
            final var matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Illegal CAPEC: " + name);
            }
            final var number = parseLong(matcher.group(1));
            return new Capec(number);
        }

        private Capec(@Nonnegative long number) {
            this.number = requireNonNegative(number, "number");
        }

        @Nonnegative
        public long number() {
            return number;
        }

        @Nonnull
        @Override
        public String name() {
            return "CAPEC-" + number();
        }

        @Nonnull
        @Override
        public Type type() {
            return capec;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof final Capec capec)) {return false;}
            return number == capec.number;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(number);
        }
    }


    enum Type {
        cve,
        cpe,
        cwe,
        capec
    }

}
