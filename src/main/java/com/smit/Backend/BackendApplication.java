package com.smit.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Backend application class.
 * 
 * @SpringBootApplication annotation is equivalent to using @Configuration,
 * @EnableAutoConfiguration and @ComponentScan with their default attributes.
 * 
 * @EnableMongoAuditing annotation enables auditing on Mongo documents.
 */
@SpringBootApplication
@EnableMongoAuditing
public class BackendApplication {

	/**
	 * Main method.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/**
	 * This method creates a WebMvcConfigurer object.
	 * 
	 * @return WebMvcConfigurer object
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}
}
