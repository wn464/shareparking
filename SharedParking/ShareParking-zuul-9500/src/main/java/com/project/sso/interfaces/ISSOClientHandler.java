package com.project.sso.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sharePark-sso")
public interface ISSOClientHandler {

	@GetMapping("/token")
	String tokenCheck(String token,String url);
}
