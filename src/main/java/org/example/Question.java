package org.example;

public class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private String correctAnswer;

    private Question(Builder builder) {
        this.questionText = builder.questionText;
        this.optionA = builder.optionA;
        this.optionB = builder.optionB;
        this.optionC = builder.optionC;
        this.optionD = builder.optionD;
        this.optionE = builder.optionE;
        this.optionF = builder.optionF;
        this.correctAnswer = builder.correctAnswer;
    }

    public static class Builder {
        private String questionText;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String optionE;
        private String optionF;
        private String correctAnswer;

        public Builder questionText(String questionText) {
            this.questionText = questionText;
            return this;
        }

        public Builder optionA(String optionA) {
            this.optionA = optionA;
            return this;
        }

        public Builder optionB(String optionB) {
            this.optionB = optionB;
            return this;
        }

        public Builder optionC(String optionC) {
            this.optionC = optionC;
            return this;
        }

        public Builder optionD(String optionD) {
            this.optionD = optionD;
            return this;
        }

        public Builder optionE(String optionE) {
            this.optionE = optionE;
            return this;
        }

        public Builder optionF(String optionF) {
            this.optionF = optionF;
            return this;
        }

        public Builder correctAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }

    @Override
    public String toString() {
        return "Question{" + '\n' +
                "questionText='" + this.questionText + '\'' + '\n' +
                ", optionA='" + this.optionA + '\'' + '\n' +
                ", optionB='" + this.optionB + '\'' +'\n' +
                ", optionC='" + this.optionC + '\'' +'\n' +
                ", optionD='" + this.optionD + '\'' +'\n' +
                ", optionE='" + this.optionE + '\'' +'\n' +
                ", optionF='" + this.optionF + '\'' +'\n' +
                ", correctAnswer='" + this.correctAnswer + '\'' +'\n' +
                '}' + '\n' ;
    }
}