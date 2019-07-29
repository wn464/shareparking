package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.controller")
public class ApplicationApp8081Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationApp8081Demo.class, args);
	}
}
