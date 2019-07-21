package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ApplicationConfigServer7000Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfigServer7000Demo.class, args);
	}
}
