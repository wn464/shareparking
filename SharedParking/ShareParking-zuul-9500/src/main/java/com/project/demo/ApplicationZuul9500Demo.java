package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ApplicationZuul9500Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationZuul9500Demo.class, args);
	}
}
