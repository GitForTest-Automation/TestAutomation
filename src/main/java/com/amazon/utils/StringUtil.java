package com.amazon.utils;

public class StringUtil {

    public static String addIndent(int indentLevel) {

        indentLevel += indentLevel;
        final StringBuilder space = new StringBuilder("");
        for (int i = 0; i < indentLevel; i++) {
            space.append("  ");
        }
        return space.toString();
    }

    public static String addUnderline(final int indentLevel) {

        final StringBuilder space = new StringBuilder("");
        for (int i = 0; i < indentLevel; i++) {
            space.append('-');
        }
        return space.toString();
    }

    public static String changeHtmlToPlain(final String text) {

        String plainText = text.replaceAll("\\<.*?\\>", "");
        return plainText;
    }
}
