package org.example;

public class Question {
    String questionText = "";
    String option1 = "";
    String option2 = "";
    String option3 = "";
    String option4 = "";
    String option5 = "";
    String option6 = "";
    String correctAnswer = "";
    String overallExplanation = "";

    public String[] toCSVRow() {
        return new String[] {
                questionText,
                option1,
                option2,
                option3,
                option4,
                option5,
                option6,
                correctAnswer,
                overallExplanation
        };
    }
}
