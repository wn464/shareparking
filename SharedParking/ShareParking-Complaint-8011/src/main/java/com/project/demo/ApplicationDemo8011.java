package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.project.controller","com.project.service"})
@MapperScan("com.project.dao")
@EnableEurekaClient     //代表客户端
public class ApplicationDemo8011 {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationDemo8011.class, args);
	}
}