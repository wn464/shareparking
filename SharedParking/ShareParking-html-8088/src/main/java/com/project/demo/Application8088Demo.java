package com.project.demo;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableRedisHttpSession
@RestController
@ServletComponentScan("com.project.filter")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Application8088Demo {

	public static void main(String[] args) {
		SpringApplication.run(Application8088Demo.class, args);
	}
	@GetMapping("/id")
	public String sessionid(HttpSession session) {
		return session.getId().toString();
	}
}
