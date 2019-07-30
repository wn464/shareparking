package com.project.controller.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.project.Bean.MemberBean;

@FeignClient(name="park-member",fallbackFactory=TestHandlerFallbackfactory.class)
public interface MemberRemoter {
	@GetMapping("/member/findbyid")
	public MemberBean findById();
	
	
	

}

