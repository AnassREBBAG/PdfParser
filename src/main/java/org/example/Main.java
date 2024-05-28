package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static boolean questionFound, optionAFound, OptionBFound, optionCFound,
            optionDFound, optionEFound, correctAnswerFound;


    public static void main(String[] args) {
        String filePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "Test_PDF_Extractor.pdf";

        ArrayList<Question> questions = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            String[] paragraphList = text.split("\n");

            int i = 0;

            while (i < paragraphList.length) {

                if (Parser.findQuestion(paragraphList[i])) {

                    Question group = new Question();
                    group.questionText = Parser.extractQuestion(paragraphList[i + 1]);
                    group.optionA = Parser.extractQuestion(paragraphList[i + 2]);
                    group.optionB = Parser.extractQuestion(paragraphList[i + 3]);
                    group.optionC = Parser.extractQuestion(paragraphList[i + 4]);
                    group.optionD = Parser.extractQuestion(paragraphList[i + 5]);
                    group.correctAnswer = Parser.extractCorrectAnswer(paragraphList[i + 6]);

                    questions.add(group);

                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Writer.writeQuestionsToExcel(questions);
        System.out.println("#################################");
    }

    
}
