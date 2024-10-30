package org.example;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("pdfTextWriter")
public class PdfTextWriter implements TextWriter {
    @Override
    public void write(String fileName, String text) {
        String filePath = fileName + ".pdf";
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph(text));
            document.close();

            System.out.println("Successfully wrote PDF to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to " + filePath);
            e.printStackTrace();
        }
    }
}
