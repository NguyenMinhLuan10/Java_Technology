package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TextEditor editor = context.getBean(TextEditor.class);

        // Nhập nội dung
        editor.input("Hello, this is a text editor.");

        // Lưu dưới dạng .txt
        editor.saveAsPlainText("output.txt");

        // Lưu dưới dạng .pdf
        editor.saveAsPdf("output");
    }
}
