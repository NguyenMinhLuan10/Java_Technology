package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Product product1() {
        return new Product(1, "Product A", 10.99, "Description A");
    }

    @Bean
    @Scope("prototype")
    public Product product2() {
        return new Product(2, "Product B", 20.99, "Description B");
    }

    @Bean
    @Scope("singleton")
    public Product product3() {
        return new Product(3, "Product C", 30.99, "Description C");
    }
}
