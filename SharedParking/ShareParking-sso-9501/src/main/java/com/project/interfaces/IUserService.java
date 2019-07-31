package com.project.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.Bean.MemberBean;
import com.project.Bean.UserBean;

import feign.Param;

@FeignClient(name = "PARK-USER")
public interface IUserService {

	@PostMapping("/user/username")
	UserBean findByName(@RequestParam(name = "username")String username);
}
