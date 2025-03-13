package org.michaelbae.recipeplanner.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class FileStorageConfig implements WebMvcConfigurer
{

    @Value("${file.upload-dir:/src/main/resources/static/assets/imgs/uploads}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    // Make it a bean to inject into other components
    @Bean
    public String uploadDir() {
        return uploadDir;
    }

    // Configure the resource handler to serve files from the external directory
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/")
                .setCachePeriod(0);  // Set to 0 to disable caching for development
    }
}