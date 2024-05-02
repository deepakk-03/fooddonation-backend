package com.cts.deepak.foodDonation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class CorsConfig implements WebMvcConfigurer {
 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Allow requests from all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow only specific HTTP methods
                .allowedHeaders("*"); // Allow all headers
    }
}