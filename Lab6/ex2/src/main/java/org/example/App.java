package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Product product1 = (Product) context.getBean("product1");
        Product product2 = (Product) context.getBean("product2");
        Product product3 = (Product) context.getBean("product3");

        System.out.println("Product 1: " + product1);
        System.out.println("Product 2: " + product2);
        System.out.println("Product 3: " + product3);

        Product product1AnotherInstance = (Product) context.getBean("product1");
        Product product2AnotherInstance = (Product) context.getBean("product2");

        System.out.println("Is product1 the same instance? " + (product1 == product1AnotherInstance));
        System.out.println("Is product2 the same instance? " + (product2 == product2AnotherInstance));
        System.out.println("Is product3 the same instance? " + (product3 == context.getBean("product3")));
    }
}
