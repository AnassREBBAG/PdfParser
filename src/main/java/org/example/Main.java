package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("starting ..");

        String content = ParserUtils.extractTextFromFile("src/main/resources/IMPUTV2.txt");

        ParserUtils.createCSVFile("Output", "Question", "QuestionType",
                "Answer Option 1", "Explanation 1",
                "Answer Option 2", "Explanation 2",
                "Answer Option 3", "Explanation 3",
                "Answer Option 4", "Explanation 4",
                "Answer Option 5", "Explanation 5",
                "Answer Option 6", "Explanation 6",
                "Correct Answer", "Overall Explanation",
                "Domain");

        // ParserUtils.extractQuestions(content).forEach(q ->
        // System.out.println(q.toString()));

        List<Question> questions = ParserUtils.extractQuestions(content);

        questions.forEach(QuestionType::setQuestionType);
        questions.forEach(ParserUtils::formatCorrectAnswer);

        questions.forEach(q -> ParserUtils.writeToCSVFile("Output.csv", q.questionText, q.questionType,
                q.option1, q.explanation1,
                q.option2, q.explanation2,
                q.option3, q.explanation3,
                q.option4, q.explanation4,
                q.option5, q.explanation5,
                q.option6, q.explanation6,
                q.correctAnswer, q.overallExplanation,
                q.domain));

    }
}
