package org.example;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfTextWriter implements TextWriter {

    @Override
    public void write(String fileName, String text) {
        // Sử dụng đuôi .pdf cho file
        String pdfFileName = fileName + ".pdf";
        try (FileOutputStream fos = new FileOutputStream(pdfFileName)) {
            fos.write(("PDF Content: " + text).getBytes());
            System.out.println("PDF content written to file: " + pdfFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
