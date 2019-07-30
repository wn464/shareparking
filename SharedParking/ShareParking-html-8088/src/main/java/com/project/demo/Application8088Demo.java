package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Application8088Demo {

	public static void main(String[] args) {
		SpringApplication.run(Application8088Demo.class, args);
	}
}
