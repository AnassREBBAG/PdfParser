package org.example;

public class Parser {

    public static boolean startsWith(String str, String prefix) {
        if (str.length() < prefix.length())
            return false;
        return str.substring(0, prefix.length()).equalsIgnoreCase(prefix);
    }
    public static boolean isQuestion(String paragraph) {
        paragraph = paragraph.replaceFirst("^\\s+", "");
        return startsWith(paragraph, "question");
    }

    public static String extractQuestion(String paragraph) {
        return paragraph.trim();
    }

    public static String extractOption(String paragraph) {
        return paragraph.length() > 2 ? paragraph.substring(2).trim() : "";
    }

    public static String extractCorrectAnswer(String paragraph) {
        return paragraph.length() > 15 ? paragraph.substring(15).trim() : "";
    }

    public static boolean isOption(String paragraph, char c) {
        return paragraph.trim().startsWith(c + ".") || paragraph.contains(" " + c + '.');
    }

    public static boolean isCorrectAnswer(String paragraph) {
        return paragraph.trim().startsWith("Correct Answer");
    }
}