package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.project.dao")
@ComponentScan({"com.project.controller","com.project.config","com.project.service"})
public class ApplicationCustomer8080Demo {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationCustomer8080Demo.class, args);
	}
}
