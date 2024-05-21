package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFToExcel {

    private static final Logger logger = LogManager.getLogger(PDFToExcel.class);

    public static String extractTextFromPDF(String filePath) {
        String fontCachePath = "C:\\Users\\AnassREBBAG\\.pdfbox.cache";
        File fontCacheDir = new File(fontCachePath);
        if (!fontCacheDir.exists()) {
            fontCacheDir.mkdirs();
        }
        System.setProperty("pdfbox.fontcache", fontCachePath);

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            logger.error("Error extracting text from PDF", e);
            return null;
        }
    }

    public static void parseAndWriteToExcel(String text, String excelFilePath) {
        String questionPattern = "(Question \\d+\\n.*?\\n)(A\\. .*?\\n)(B\\. .*?\\n)(C\\. .*?\\n)(D\\. .*?\\n)(E\\. .*?\\n)(Correct Answer: [A-E])";
        Pattern pattern = Pattern.compile(questionPattern, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Questions");

        int rowCount = 0;

        while (matcher.find()) {
            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(matcher.group(1).trim());  // Question
            row.createCell(1).setCellValue(matcher.group(2).trim());  // Option A
            row.createCell(2).setCellValue(matcher.group(3).trim());  // Option B
            row.createCell(3).setCellValue(matcher.group(4).trim());  // Option C
            row.createCell(4).setCellValue(matcher.group(5).trim());  // Option D
            row.createCell(5).setCellValue(matcher.group(6).trim());  // Option E
            row.createCell(6).setCellValue(matcher.group(7).trim());  // Correct Answer
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            logger.error("Error writing to Excel file", e);
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.error("Error closing the workbook", e);
            }
        }
    }

    public static void main(String[] args) {
        String pdfFilePath = "src/main/resources/Test_PDF_Extractor.pdf";
        String excelFilePath = "src/main/resources/Book.xlsx";

        String text = extractTextFromPDF(pdfFilePath);
        if (text != null) {
            parseAndWriteToExcel(text, excelFilePath);
        }
    }
}
