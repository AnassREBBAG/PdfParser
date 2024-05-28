package org.example;

public class Parser {

    public static boolean findQuestion(String paragraph) {

        String[] wordList = paragraph.split(" ");

        // System.out.println("-> " + wordList[0]);
        if (wordList[0].equals("Question"))
            return true;
        return false;

    }

    public static String extractQuestion(String paragraph) {

        // StringBuilder question = new StringBuilder();
        //
        // String[] wordList = paragraph.split(" ");
        //
        //
        // for (int i = 0; i < wordList.length ; i++) {
        // question.append(wordList[i] + " ");
        // }
        //
        // return question.toString();

        return paragraph;
    }

    public static String extractOption(String paragraph) {

        return paragraph.substring(1);

    }

    public static String extractCorrectAnswer(String paragraph) {
        return paragraph.substring(15);
    }

}
