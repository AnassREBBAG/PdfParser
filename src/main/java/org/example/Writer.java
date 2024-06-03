
package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writer {
    
    public static void writeQuestionsToExcel(ArrayList<Question> questions) {
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
        headerRow.createCell(5).setCellValue("Option E");
        headerRow.createCell(6).setCellValue("Option F");
        headerRow.createCell(7).setCellValue("Correct Answer");

        // Populate rows with question data
        for (Question question : questions) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(question.questionText);
            row.createCell(1).setCellValue(question.optionA);
            row.createCell(2).setCellValue(question.optionB);
            row.createCell(3).setCellValue(question.optionC);
            row.createCell(4).setCellValue(question.optionD);
            row.createCell(5).setCellValue(question.optionE);
            row.createCell(6).setCellValue(question.optionF);
            row.createCell(7).setCellValue(question.correctAnswer);
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
