package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@EnableEurekaClient
@SpringBootApplication
@ComponentScan({"com.project.service"})
@ComponentScan({"com.project.controller"})
@MapperScan("com.project.dao")
public class ApplicationDemo1 {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationDemo1.class, args);
	}
}
