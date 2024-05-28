package org.example;

public class Parser {

    public static boolean isQuestion(String paragraph) {

        String[] wordList = paragraph.split(" ");

        if (wordList.length <= 0)
            return false;
        // System.out.println("-> " + wordList[0]);
        if (wordList[0].equals("Question"))
            return true;
        return false;

    }

    public static String extractQuestion(String paragraph) {

        return paragraph;
    }

    public static String extractOption(String paragraph) {

        return paragraph.substring(1);

    }

    public static String extractCorrectAnswer(String paragraph) {
        if (paragraph.length() <= 13)
            return " ";

        return paragraph.substring(15);
    }

    public static boolean isOption(String paragraph, char c) {

        if (paragraph.length() <= 2)
            return false;
        if (paragraph.charAt(0) == c)
            return true;
        return false;

    }

    public static boolean isCorrectAnswer(String paragraph) {
        if (paragraph.length() < 14)
            return false;
        if (paragraph.substring(0, 13).equals("Correct Answer"))
            return true;
        return false;
    }
}
