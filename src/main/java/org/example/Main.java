package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static boolean questionFound, optionAFound, OptionBFound, optionCFound,
            optionDFound, optionEFound, correctAnswerFound;

    // static Question group ;

    public static void main(String[] args) {
        // String filePath = "src/main/resources/Test_PDF_Extractor3.pdf";
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "Test_PDF_Extractor3.pdf";

        ArrayList<Question> questions = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            String[] paragraphList = text.split("\n");

            int i = 0;

            while (i < paragraphList.length) {

                if (Parser.findQuestion(paragraphList[i])) {

                    // Question group = new Question.Builder()
                    // .optionA(Parser.extractOption(paragraphList[i+2]))
                    // .optionB(Parser.extractOption(paragraphList[i+3]))
                    // .optionC(Parser.extractOption(paragraphList[i+4]))
                    // .optionD(Parser.extractOption(paragraphList[i+5]))
                    // .correctAnswer(Parser.extractCorrectAnswer(paragraphList[i+6]))
                    // .build();

                    Question group = new Question();
                    group.questionText = Parser.extractQuestion(paragraphList[i + 1]);
                    group.optionA = Parser.extractQuestion(paragraphList[i + 2]);
                    group.optionB = Parser.extractQuestion(paragraphList[i + 3]);
                    group.optionC = Parser.extractQuestion(paragraphList[i + 4]);
                    group.optionD = Parser.extractQuestion(paragraphList[i + 6]);
                    // group.optionE = Parser.extractQuestion(paragraphList[i+6]);
                    group.correctAnswer = Parser.extractCorrectAnswer(paragraphList[i + 6]);

                    questions.add(group);
                    // System.out.println(group.toString());
                    // System.out.println(paragraphList[i+1]);
                    // System.out.println(paragraphList[i+2]);
                    // System.out.println(paragraphList[i+3]);
                    // System.out.println(paragraphList[i+4]);
                    // System.out.println(paragraphList[i+5]);
                    // System.out.println(paragraphList[i+6]);

                }
                i++;

            }

            // todo : write parsing code

            questions.forEach(group -> System.out.println(group.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(group.toString());
        System.out.println("#################################");

    }
}
