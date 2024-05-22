package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFParser {
    public static void main(String[] args) {
        String filePath = "src\\main\\resources\\Test_PDF_Extractor3.pdf";
        ArrayList<Question> questions = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            Pattern questionPattern = Pattern.compile("Question \\d+\\n(.*?)\\nA\\. (.*?)\\nB\\. (.*?)\\nC\\. (.*?)\\nD\\. (.*?)\\nCorrect\\.Answer\\:.+?(.*?)", Pattern.DOTALL);
            Matcher matcher = questionPattern.matcher(text);

            System.out.println(matcher.groupCount());

            System.out.println(matcher.group());

            while (matcher.find()) {
                String questionText = matcher.group(1).trim();
                String optionA = matcher.group(2).trim();
                String optionB = matcher.group(3).trim();
                String optionC = matcher.group(4).trim();
                String optionD = matcher.group(5).trim();
                String correctAnswer = matcher.group(6).trim();

                questions.add(new Question(questionText, optionA, optionB, optionC, optionD, correctAnswer));
            }


            for (Question question : questions) {
                System.out.println(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(111111111);
        }
    }
}
