package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {
    private String text;

    private final TextWriter plainTextWriter;
    private final TextWriter pdfTextWriter;

    @Autowired
    public TextEditor(@Qualifier("plainTextWriter") TextWriter plainTextWriter,
                      @Qualifier("pdfTextWriter") TextWriter pdfTextWriter) {
        this.plainTextWriter = plainTextWriter;
        this.pdfTextWriter = pdfTextWriter;
    }

    public void input(String text) {
        this.text = text;
    }

    public void saveAsPlainText(String fileName) {
        plainTextWriter.write(fileName, text);
    }

    public void saveAsPdf(String fileName) {
        pdfTextWriter.write(fileName, text);
    }
}
