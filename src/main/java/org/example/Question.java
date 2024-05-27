package org.example;

public class Question {
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public String optionE;
    public String optionF;
    public String correctAnswer;
    public String questionText;

    public Question() {

        this.questionText = " ";
        this.optionA = " ";
        this.optionB = " ";
        this.optionC = " ";
        this.optionD = " ";
        this.optionE = " ";
        this.optionF = " ";
        this.correctAnswer = " ";

    }

    @Override
    public String toString() {
        return "Question{" + '\n' +
                "questionText='" + this.questionText + '\'' + '\n' +
                "optionA='" + this.optionA + '\'' + '\n' +
                "optionB='" + this.optionB + '\'' + '\n' +
                "optionC='" + this.optionC + '\'' + '\n' +
                "optionD='" + this.optionD + '\'' + '\n' +
                "optionE='" + this.optionE + '\'' + '\n' +
                "optionF='" + this.optionF + '\'' + '\n' +
                "correctAnswer='" + this.correctAnswer + '\'' + '\n' +
                '}' + '\n';
    }
}