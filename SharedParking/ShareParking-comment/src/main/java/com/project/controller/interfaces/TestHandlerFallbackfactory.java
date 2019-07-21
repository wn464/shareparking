package com.project.controller.interfaces;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
@Component
public class TestHandlerFallbackfactory implements FallbackFactory<ITestHandler>{

	@Override
	public ITestHandler create(Throwable cause) {
		// TODO Auto-generated method stub
		return new ITestHandler() {
			
			@Override
			public String getTest() {
				
				return "降级";
			}
		};
	}

}
