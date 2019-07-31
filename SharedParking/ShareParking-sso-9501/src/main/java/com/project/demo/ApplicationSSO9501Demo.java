package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableRedisHttpSession
@EnableFeignClients(basePackages = "com.project.interfaces")
@ComponentScan({"com.project.controller","com.project.interfaces"})
public class ApplicationSSO9501Demo {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationSSO9501Demo.class, args);
	}
}
