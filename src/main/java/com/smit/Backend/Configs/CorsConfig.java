package com.smit.Backend.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Define the endpoint you want to enable CORS for
                        .allowedOrigins("http://localhost") // Allow requests from this origin
                        .allowedMethods("*") // Allowed HTTP methods
                        .allowCredentials(true); // Allow sending cookies
            }
        };
    }
}
