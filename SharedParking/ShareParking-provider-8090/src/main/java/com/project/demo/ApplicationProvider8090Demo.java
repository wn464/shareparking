package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApplicationProvider8090Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationProvider8090Demo.class, args);
	}
}
