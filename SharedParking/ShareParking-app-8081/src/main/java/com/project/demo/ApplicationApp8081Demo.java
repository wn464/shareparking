package com.project.demo;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.project.controller")
@RestController
public class ApplicationApp8081Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationApp8081Demo.class, args);
	}
	@GetMapping("/id")
	public String id(HttpSession session) {
		return session.getAttribute("userid").toString();
	}
}
