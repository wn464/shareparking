package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationCustomer8080Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationCustomer8080Demo.class, args);
	}
}
