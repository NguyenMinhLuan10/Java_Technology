package org.example.config;

import org.example.AppInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.author}")
    private String appAuthor;

    @Bean
    public AppInfo appInfo() {
        return new AppInfo(appName, appVersion, appAuthor);
    }
}
