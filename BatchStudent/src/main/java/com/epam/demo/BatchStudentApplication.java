package com.epam.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class BatchStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchStudentApplication.class, args);
	}
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
