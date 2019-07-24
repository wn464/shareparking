package com.project.controller.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "WONIUCLOUD-PROVIDER" ,fallbackFactory = TestHandlerFallbackfactory.class)
public interface ITestHandler {
	@GetMapping("/test")
	public String getTest();
}
