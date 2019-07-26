package com.project.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@Configuration
public class hystrixConfig {
	@Bean
	public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet(){
		HystrixMetricsStreamServlet servlet=new HystrixMetricsStreamServlet();
ServletRegistrationBean<HystrixMetricsStreamServlet> srb=new ServletRegistrationBean<HystrixMetricsStreamServlet>(servlet);
srb.addUrlMappings("/hystrix/stream");
srb.setName("hystrixMetricsStreamServlet");
return srb;
	}
}
