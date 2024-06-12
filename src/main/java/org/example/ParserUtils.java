package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

public class ParserUtils {

    public static String clean(String s) {

        if (s == null)
            return "";
        return s.replace(",", " ");

    }

    public static String replaceUppercaseWithOrder(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                // Calculate the alphabetical order (A=1, B=2, ..., Z=26)
                int order = c - 'A' + 1;
                result.append(order);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String extractTextFromFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return "";
    }

    public static List<Question> extractQuestions(String text) {
        String[] lines = text.split("\n");
        List<Question> questions = new ArrayList<>();
        Question q = null;

        for (int i = 0; i < lines.length; i++) {
            lines[i] = clean(lines[i]);

        }

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            if (line.startsWith("Question")) {
                if (q != null) {
                    questions.add(q);
                }
                q = new Question();
                i++;
                q.questionText = lines[i].trim();
                while (i + 1 < lines.length && !lines[i + 1].startsWith("A. ")) {
                    q.questionText += " " + lines[++i].trim();
                }
            } else if (line.startsWith("A. ")) {
                q.option1 = line;
                while (i + 1 < lines.length && !lines[i + 1].startsWith("B. ")) {
                    q.option1 += " " + lines[++i].trim();
                }
            } else if (line.startsWith("B. ")) {
                q.option2 = line;
                while (i + 1 < lines.length && !lines[i + 1].startsWith("C. ")) {
                    q.option2 += " " + lines[++i].trim();
                }
            } else if (line.startsWith("C. ")) {
                q.option3 = line;
                while (i + 1 < lines.length && !lines[i + 1].startsWith("D. ")) {
                    q.option3 += " " + lines[++i].trim();
                }
            } else if (line.startsWith("D. ")) {
                q.option4 = line;
                while (i + 1 < lines.length && !lines[i + 1].startsWith("E. ")
                        && !lines[i + 1].startsWith("Correct Answer")) {
                    q.option4 += " " + lines[++i].trim();
                }
            } else if (line.startsWith("E. ")) {
                q.option5 = line;
                while (i + 1 < lines.length && !lines[i + 1].startsWith("F. ")
                        && !lines[i + 1].startsWith("Correct Answer")) {
                    q.option5 += " " + lines[++i].trim();
                }
            } else if (line.startsWith("F. ")) {
                q.option6 = line;
                while (i + 1 < lines.length && !lines[i + 1].startsWith("Correct Answer")) {
                    q.option6 += " " + lines[++i].trim();
                }
            } else if (line.startsWith("Correct Answer")) {
                q.correctAnswer = replaceUppercaseWithOrder(line.split(":")[1].trim());
            } else if (line.startsWith("Explanation")) {
                q.overallExplanation = line.substring(12);
                while (i + 1 < lines.length && !lines[i + 1].startsWith("Question")) {
                    q.overallExplanation += lines[++i].trim() + " ";
                }
            }
        }

        if (q != null) {
            questions.add(q);
        }

        return questions;
    }

    public static void createCSVFile(String fileName, String... headers) {
        try (FileWriter fileWriter = new FileWriter(fileName + ".csv")) {
            for (int i = 0; i < headers.length; i++) {
                fileWriter.append(headers[i]);
                if (i < headers.length - 1) {
                    fileWriter.append(",");
                }
            }
            fileWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToCSVFile(String fileName, String... data) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {

            for (int i = 0; i < data.length; i++) {
                fileWriter.append(data[i]);
                if (i < data.length - 1) {
                    fileWriter.append(",");
                }
            }
            fileWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void formatCorrectAnswer(Question q) {

        if (q.correctAnswer == null || q.correctAnswer.isEmpty()) {
            return;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < q.correctAnswer.length(); i++) {
            result.append(q.correctAnswer.charAt(i));
            if (i < q.correctAnswer.length() - 1) {
                result.append(' ');
            }
        }

        q.correctAnswer = result.toString();
    }

}
