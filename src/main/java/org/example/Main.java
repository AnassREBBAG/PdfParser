package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Test_PDF_Extractor.pdf";

        ArrayList<Question> questions = new ArrayList<>();

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

            String[] paragraphList = text.split("\n");
            int i = 0;

            while (i < paragraphList.length) {
                if (Parser.isQuestion(paragraphList[i])) {
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
                }
            }
        }

        return questions;
    }
}
