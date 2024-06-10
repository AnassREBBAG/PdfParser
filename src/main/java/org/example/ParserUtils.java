package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

public class ParserUtils {

    public static String extractTextFromFile(String path) {

        try {

            String text = Files.readString(Paths.get(path));
            return text;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return " ";

    }

    // todo : extract questions and write into csv file
    public static void extractQuestions(String text) {

        String[] lines = text.split("\n");

        boolean questionFound;

        Question q = new Question();

        int i = 0;
        while (i < lines.length) {

            if (lines[i].startsWith("Question")) {
                i++;
                questionFound = true;

                q = new Question();

            }

            else if (lines[i].startsWith("A. ")) {
                while (!lines[i].startsWith("B. ")) {
                    q.option1 = q.option1 + " " + lines[i];
                    i++;

                }

            }

            else if (lines[i].startsWith("B. ")) {
                while (!lines[i].startsWith("C. ")) {
                    q.option2 = q.option2 + " " + lines[i];
                    i++;

                }

            } else if (lines[i].startsWith("C. ")) {
                while (!lines[i].startsWith("D. ")) {
                    q.option3 = q.option3 + " " + lines[i];
                    i++;

                }

            } else if (lines[i].startsWith("D. ")) {
                while (!lines[i].startsWith("E. ")) {
                    q.option4 = q.option4 + " " + lines[i];
                    i++;

                }

            } else if (lines[i].startsWith("E. ")) {
                while (!lines[i].startsWith("F. ")) {
                    q.option5 = q.option5 + " " + lines[i];
                    i++;

                }

            }

            else if (lines[i].startsWith("F. ")) {
                while (!lines[i].startsWith("Correct Answer")) {
                    q.option6 = q.option6 + " " + lines[i];
                    i++;

                }

            }

            else if (lines[i].startsWith("Correct Answer")) {
                q.correctAnswer = lines[i];
                i++;

            }

            else if (lines[i].startsWith("Explanation")) {

                while (i < lines.length) {

                    if (lines[i].startsWith("Question"))
                        break;
                    else {
                        q.overallExplanation = q.overallExplanation + " " + lines[i];
                        i++;
                    }
                }

            }

            else {
                q.questionText = q.questionText + " " + lines[i];
                i++;

            }

        }

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
            // Write the data to the CSV file
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

}
