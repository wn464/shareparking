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
public class ApplicationProvider8090Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationProvider8090Demo.class, args);
	}
}
