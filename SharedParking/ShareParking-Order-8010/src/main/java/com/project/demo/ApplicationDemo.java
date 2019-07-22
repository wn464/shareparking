package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.project.service"})
@MapperScan("com.project.dao")
public class ApplicationDemo {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationDemo.class, args);
	}
}
