package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParserUtils {


    public static String extractTextFromFile(String path){

        try {
      
            String text = Files.readString(Paths.get(path));
            return text;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return " ";

    }



    //todo : extract questions and write into csv file
    public static void extractQuestions(String text){

        String[] lines = text.split("\n");


        for (int i = 0; i < lines.length; i++) {
            
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
