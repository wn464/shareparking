package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.project.util")
@MapperScan("com.project.dao")
@ComponentScan("com.project.controller")
@ComponentScan("com.project.service")
@ComponentScan("com.project.config")
@EnableFeignClients(basePackages = "com.project.controller.interfaces")
public class Application8002Demo {
    public static void main(String[] args) {
        SpringApplication.run(Application8002Demo.class,args);
    }
}
