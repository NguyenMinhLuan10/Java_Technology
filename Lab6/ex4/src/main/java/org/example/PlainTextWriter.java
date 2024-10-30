package org.example;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component("plainTextWriter")
public class PlainTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        String filePath = fileName + ".txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(text);
            System.out.println("Successfully wrote plain text to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to " + filePath);
            e.printStackTrace();
        }
    }
}
