package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.controller")
@ComponentScan("com.project.service")
@ComponentScan("com.project.config")
@ComponentScan("com.project.shiro")
@ComponentScan("com.project.util")
@MapperScan("com.project.dao")
@EnableEurekaClient
public class Application8001Demo {
    public static void main(String[] args) {
        SpringApplication.run(Application8001Demo.class,args);
    }
}
