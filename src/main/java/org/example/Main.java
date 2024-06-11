package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("starting ..");

        String content = ParserUtils.extractTextFromFile("src/main/resources/IMPUTV2.txt");

        ParserUtils.createCSVFile("Output",
                "Question",
                " Option 1",
                " Option 2",
                " Option 3",
                " Option 4",
                " Option 5",
                " Option 6",
                "Correct Answer",
                "Overall Explanation");

        List<Question> questions = ParserUtils.extractQuestions(content);
        ParserUtils.writeToCSVFile("Output", questions);
    }
}
