package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.project.controller")
@ComponentScan("com.project.Serviceimpl")
@MapperScan("com.project.dao")
@ComponentScan("com.project.util")
@ComponentScan("com.project.config")
@EnableEurekaClient
@EnableScheduling
public class Application8050demo {
public static void main(String[] args) {
	SpringApplication.run(Application8050demo.class, args);
}
}
