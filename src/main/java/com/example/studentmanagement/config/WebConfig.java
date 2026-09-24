package com.example.studentmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Uploaded student photos are stored outside the packaged jar, in an
 * "uploads/" folder next to the application (see FileStorageConfig /
 * application.properties: app.upload.dir). This maps that folder to the
 * public /uploads/** URL so <img> tags can load them directly.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
