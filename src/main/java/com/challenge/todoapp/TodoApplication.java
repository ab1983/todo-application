package com.challenge.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.challenge.todoapp.service.AccessService;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages="com.example")
public class TodoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TodoApplication.class, args);
		//context.getBean(AccessService.class).populateDatabase();		
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }	
}
