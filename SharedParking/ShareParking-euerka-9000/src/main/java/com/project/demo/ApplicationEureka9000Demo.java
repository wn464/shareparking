package com.project.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka9000Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationEureka9000Demo.class, args);
	}
}
