package org.example;

import java.util.List;

public class QuestionType {

    public static String MULTIPLE_CHOICE = "multiple-choice";
    public static String MULTI_SELECT = "multi-select";

    public static void setQuestionType(Question q) {

        q.questionType = q.correctAnswer.length() > 1 ? MULTI_SELECT : MULTIPLE_CHOICE;

    }

}
