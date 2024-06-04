package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "GCP-229ques.pdf";

        ArrayList<Question> questions = new ArrayList<>();

        //try-catch
        try {
            questions = parsePDF(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Writer.writeQuestionsToExcel(questions);
        System.out.println("Questions successfully written to Excel file.");
    }

    public static ArrayList<Question> parsePDF(String filePath) throws IOException {
        ArrayList<Question> questions = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);


            //System.out.println(text);


            String[] paragraphList = text.split("\n");

            //for(int i=0; i < paragraphList.length; i++)
              //  System.out.println(i + "  " + paragraphList[i]);

            int i = 0;

            StringBuilder builder = new StringBuilder();
            boolean questionFound = false;
            Question question = new Question();
            while (i < paragraphList.length) {

                if(Parser.isQuestion(paragraphList[i])) {
                    questionFound = true;
                    question = new Question();
                }
                else if(questionFound) {
                    if (Parser.isOption(paragraphList[i], 'A')) {
                        question.questionText = builder.toString();
                        builder = new StringBuilder();
                        builder.append(paragraphList[i].substring(paragraphList[i].indexOf("A.")));
                    } else if (Parser.isOption(paragraphList[i], 'B')) {
                        question.optionA = builder.toString();
                        builder = new StringBuilder();
                        builder.append(paragraphList[i].substring(paragraphList[i].indexOf("B.")));
                    } else if (Parser.isOption(paragraphList[i], 'C')) {
                        question.optionB = builder.toString();
                        builder = new StringBuilder();
                        builder.append(paragraphList[i].substring(paragraphList[i].indexOf("C.")));
                    } else if (Parser.isOption(paragraphList[i], 'D')) {
                        question.optionC = builder.toString();
                        builder = new StringBuilder();
                        builder.append(paragraphList[i].substring(paragraphList[i].indexOf("D.")));
                    } else if (Parser.isOption(paragraphList[i], 'E')) {
                        question.optionD = builder.toString();
                        builder = new StringBuilder();
                        builder.append(paragraphList[i].substring(paragraphList[i].indexOf("E.")));
                    } else if (Parser.isOption(paragraphList[i], 'F')) {
                        question.optionE = builder.toString();
                        builder = new StringBuilder();
                        builder.append(paragraphList[i].substring(paragraphList[i].indexOf("F.")));
                    } else if (Parser.isCorrectAnswer(paragraphList[i])) {
                        if (question.optionA.isEmpty()) question.optionA = builder.toString();
                        else if (question.optionB.isEmpty()) question.optionB = builder.toString();
                        else if (question.optionC.isEmpty()) question.optionC = builder.toString();
                        else if (question.optionD.isEmpty()) question.optionD = builder.toString();
                        else if (question.optionE.isEmpty()) question.optionE = builder.toString();
                        else question.optionF = builder.toString();
                        String newPara = paragraphList[i].trim();
                        question.correctAnswer = newPara.substring(newPara.lastIndexOf(' '));
                        questionFound = false;
                        questions.add(question);
                        builder = new StringBuilder();
                    } else {
                        builder.append(" ").append(paragraphList[i].trim());
                    }
                }
                i++;






                /*if (Parser.isQuestion(paragraphList[i])) {
                    Question question = new Question();

                    question.questionText = paragraphList[++i].trim(); // Next paragraph is the question text
                    i++;

                    while (i < paragraphList.length && !Parser.isQuestion(paragraphList[i])) {
                        if (Parser.isOption(paragraphList[i], 'A')) {
                            question.optionA = Parser.extractOption(paragraphList[i]);
                        } else if (Parser.isOption(paragraphList[i], 'B')) {
                            question.optionB = Parser.extractOption(paragraphList[i]);
                        } else if (Parser.isOption(paragraphList[i], 'C')) {
                            question.optionC = Parser.extractOption(paragraphList[i]);
                        } else if (Parser.isOption(paragraphList[i], 'D')) {
                            question.optionD = Parser.extractOption(paragraphList[i]);
                        } else if (Parser.isOption(paragraphList[i], 'E')) {
                            question.optionE = Parser.extractOption(paragraphList[i]);
                        } else if (Parser.isOption(paragraphList[i], 'F')) {
                            question.optionF = Parser.extractOption(paragraphList[i]);
                        } else if (Parser.isCorrectAnswer(paragraphList[i])) {
                            question.correctAnswer = Parser.extractCorrectAnswer(paragraphList[i]);
                            i++; // Move to the next paragraph after the correct answer
                            break; // Stop processing current question
                        } else {
                            question.questionText += " " + paragraphList[i].trim();
                        }
                        i++;
                    }
                    questions.add(question);
                } else {
                    i++;
                }*/
            }
        }

        return questions;
    }
}