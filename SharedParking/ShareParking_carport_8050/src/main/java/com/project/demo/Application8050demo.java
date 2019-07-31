package com.project.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.project.controller")
@ComponentScan("com.project.Serviceimpl")
@MapperScan("com.project.dao")
@ComponentScan("com.project.util")
@ComponentScan("com.project.config")
@EnableFeignClients(basePackages="com.project.controller.interfaces")
@EnableEurekaClient
@EnableScheduling
@EnableCircuitBreaker //开启熔断器
public class Application8050demo {
public static void main(String[] args) {
	SpringApplication.run(Application8050demo.class, args);
}
}
