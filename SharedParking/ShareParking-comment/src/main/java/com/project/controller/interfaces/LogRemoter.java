package com.project.controller.interfaces;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="park-log",fallbackFactory=TestHandlerFallbackfactory.class)
public interface LogRemoter {
	
	//添加日志
	@PostMapping("/log/{username}/{message}")
	public Integer insertLog(@PathVariable("username")String username,@PathVariable("message")String message);
	
	
}

