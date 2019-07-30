package com.project.demo;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@EnableZuulProxy
@EnableRedisHttpSession
@RestController
@ComponentScan({"com.project.sso","com.project.filter"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ApplicationZuul9500Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationZuul9500Demo.class, args);
	}
	@GetMapping("/zuul/sessionid")
	public String sessionid(HttpSession session) {
		return session.getId().toString();
	}
}
