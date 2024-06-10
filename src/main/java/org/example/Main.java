package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("starting ..");

        String content = ParserUtils.extractTextFromFile("src/main/resources/IMPUTV2.txt");

        ParserUtils.createCSVFile("Output",
                "Question", "QuestionType",
                "Answer Option 1", "Explanation 1",
                "Answer Option 2", "Explanation 2",
                "Answer Option 3", "Explanation 3",
                "Answer Option 4", "Explanation 4",
                "Answer Option 5", "Explanation 5",
                "Answer Option 6", "Explanation 6",
                "Correct Answer", "Overall Explanation",
                "Domain");

        ParserUtils.extractQuestions(content);

    }
}
