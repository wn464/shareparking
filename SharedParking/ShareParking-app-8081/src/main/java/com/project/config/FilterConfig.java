package com.project.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.filter.AppFilter;

//@Configuration
public class FilterConfig {

	@Bean  
    public FilterRegistrationBean<Filter>  filterRegistrationBean(AppFilter filter) {  
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>();  
        registrationBean.setFilter(filter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/user/*");
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }  

}
