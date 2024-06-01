package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Writer {

    public static void writeQuestionsToExcel(List<Question> questions) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Questions");

        int rowNum = 0;
        for (Question question : questions) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(question.questionText);
            row.createCell(1).setCellValue(question.optionA);
            row.createCell(2).setCellValue(question.optionB);
            row.createCell(3).setCellValue(question.optionC);
            row.createCell(4).setCellValue(question.optionD);
            row.createCell(5).setCellValue(question.optionE);
            row.createCell(6).setCellValue(question.correctAnswer);
        }

        try (FileOutputStream fileOut = new FileOutputStream("questions.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
