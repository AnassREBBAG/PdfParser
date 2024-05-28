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
                + "Test_PDF_Extractor3.pdf";

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

        writeQuestionsToExcel(questions);
        System.out.println("#################################");
    }

    private static void writeQuestionsToExcel(ArrayList<Question> questions) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Questions");

        int rowCount = 0;

        // Create header row
        Row headerRow = sheet.createRow(rowCount++);
        headerRow.createCell(0).setCellValue("Question");
        headerRow.createCell(1).setCellValue("Option A");
        headerRow.createCell(2).setCellValue("Option B");
        headerRow.createCell(3).setCellValue("Option C");
        headerRow.createCell(4).setCellValue("Option D");
        headerRow.createCell(5).setCellValue("Correct Answer");

        // Populate rows with question data
        for (Question question : questions) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(question.questionText);
            row.createCell(1).setCellValue(question.optionA);
            row.createCell(2).setCellValue(question.optionB);
            row.createCell(3).setCellValue(question.optionC);
            row.createCell(4).setCellValue(question.optionD);
            row.createCell(5).setCellValue(question.correctAnswer);
        }

        try (FileOutputStream outputStream = new FileOutputStream("Questions.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
