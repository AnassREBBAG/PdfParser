package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "Test_PDF_Extractor.pdf";

        ArrayList<Question> questions = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            String[] paragraphList = text.split("\n");

            // System.out.println(paragraphList.length);
            int i = 0;

            while (i < paragraphList.length) {

                if (Parser.isQuestion(paragraphList[i])) {

                    Question group = new Question();
                    StringBuilder questionTextBuilder = new StringBuilder();
                    i++;

                    try {

                        questionTextBuilder.append(" " + Parser.extractQuestion(paragraphList[i]));

                        // i++;

                        while (true) {
                            if (!(Parser.isOption(paragraphList[i], 'A'))
                                    && !(Parser.isCorrectAnswer(paragraphList[i]))) {
                                questionTextBuilder.append(" " + paragraphList[i]);
                                i++;
                            }

                            if (Parser.isCorrectAnswer(paragraphList[i])) {
                                group.correctAnswer = Parser.extractCorrectAnswer(paragraphList[i]);
                                i++;

                                break;
                            }

                            if (Parser.isOption(paragraphList[i], 'A')) {
                                group.optionA = Parser.extractOption(paragraphList[i]);
                                i++;
                            }
                            if (Parser.isOption(paragraphList[i], 'B')) {
                                group.optionB = Parser.extractOption(paragraphList[i]);
                                i++;
                            }
                            if (Parser.isOption(paragraphList[i], 'C')) {
                                group.optionC = Parser.extractOption(paragraphList[i]);
                                i++;
                            }
                            if (Parser.isOption(paragraphList[i], 'D')) {
                                group.optionD = Parser.extractOption(paragraphList[i]);
                                i++;
                            }
                            if (Parser.isOption(paragraphList[i], 'E')) {
                                group.optionE = Parser.extractOption(paragraphList[i]);
                                i++;
                            }

                        }

                        group.questionText = questionTextBuilder.toString();
                        questions.add(group);

                        // group.optionA = Parser.extractOption(paragraphList[i]);
                        // group.optionB = Parser.extractOption(paragraphList[i]);
                        // group.optionC = Parser.extractOption(paragraphList[i]);
                        // group.optionD = Parser.extractOption(paragraphList[i]);
                        // group.correctAnswer = Parser.extractCorrectAnswer(paragraphList[i + 6]);

                        // questions.add(group);
                    }

                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Not enough elements remaining in the array to process a complete question");
                        e.printStackTrace();
                    }
                }

                else
                    i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Writer.writeQuestionsToExcel(questions);
        System.out.println("#################################");
    }
}
