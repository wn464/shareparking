package com.project.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.project.Bean.OrderBean;

@Component
@Aspect
public class MyAspect {
	
	
	
	/**
	 * 后置通知要执行的
	 */
	@Around("execution(public * insertOrder(..)&& args(param1,param2))")
	public void after(){

		System.out.println("MyAspect=======后置通知"+param1);
	}



}
