package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TextEditor editor = context.getBean(TextEditor.class);

        editor.input("Hello, this is a text editor using automatic bean scanning.");

        editor.saveAsPlainText("output");

        editor.saveAsPdf("output");
    }
}
