package com.project.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Bean.MemberBean;
import com.project.Bean.UserBean;

@FeignClient(name = "park-user")
public interface IUserService {

	@GetMapping("/user/username")
	UserBean findByName(String username);
}
