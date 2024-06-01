package org.example;

public class Parser {

    public static boolean isQuestion(String paragraph) {
        String[] wordList = paragraph.split(" ");
        return wordList.length > 0 && wordList[0].equals("Question");
    }

    public static String extractQuestion(String paragraph) {
        return paragraph;
    }

    public static String extractOption(String paragraph) {
        return paragraph.length() > 2 ? paragraph.substring(2).trim() : "";
    }

    public static String extractCorrectAnswer(String paragraph) {
        return paragraph.length() > 15 ? paragraph.substring(15).trim() : "";
    }

    public static boolean isOption(String paragraph, char c) {
        return paragraph.length() > 1 && paragraph.charAt(0) == c && paragraph.charAt(1) == ')';
    }

    public static boolean isCorrectAnswer(String paragraph) {
        return paragraph.length() >= 14 && paragraph.startsWith("Correct Answer");
    }
}
