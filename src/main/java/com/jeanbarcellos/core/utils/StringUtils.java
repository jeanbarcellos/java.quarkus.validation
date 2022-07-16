package com.jeanbarcellos.core.utils;

import java.util.regex.Pattern;

public class StringUtils {

    private static final String REGEX_ONLY_NUMBERS = "[\\D]";

    public static final String EMPTY = "";
    public static final String SPACE = " ";

    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String replaceAll(String regex, String replacement, String string) {
        return Pattern.compile(regex).matcher(string).replaceAll(replacement);
    }

    public static String leftPad(final String string, final Integer size, final String padChar) {
        var format = "%1$" + size + "s";
        return String.format(format, string).replace(" ", padChar);
    }

    public static String onlyNumbers(String string) {
        return string.replaceAll(REGEX_ONLY_NUMBERS, EMPTY);
    }

}
