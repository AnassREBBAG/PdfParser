package org.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFParser {

    public static void parseQuestions(String text) {
        String questionPattern = "(Question \\d+\\n.*?\\n)(A\\. .*?\\n)(B\\. .*?\\n)(C\\. .*?\\n)(D\\. .*?\\n)(E\\. .*?\\n)(Correct Answer: [A-E])";
        Pattern pattern = Pattern.compile(questionPattern, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("Question: " + matcher.group(1));
            System.out.println("Option A: " + matcher.group(2));
            System.out.println("Option B: " + matcher.group(3));
            System.out.println("Option C: " + matcher.group(4));
            System.out.println("Option D: " + matcher.group(5));
            System.out.println("Option E: " + matcher.group(6));
            System.out.println("Correct Answer: " + matcher.group(7));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/resources/GCP-229ques.pdf";
        String text = PDFReader.extractTextFromPDF(filePath);
        if (text != null) {
            parseQuestions(text);
        }
    }
}
