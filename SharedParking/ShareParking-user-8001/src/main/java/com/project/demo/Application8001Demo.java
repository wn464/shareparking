package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.controller")
@ComponentScan("com.project.service")
@ComponentScan("com.project.config")
@ComponentScan("com.project.shiro")
@ComponentScan("com.project.util")
@MapperScan("com.project.dao")
public class Application8001Demo {
    public static void main(String[] args) {
        SpringApplication.run(Application8001Demo.class,args);
    }
}
