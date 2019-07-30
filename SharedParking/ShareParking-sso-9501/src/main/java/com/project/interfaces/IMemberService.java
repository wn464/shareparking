package com.project.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.Bean.MemberBean;

@FeignClient(name = "park-member")
public interface IMemberService {

	@GetMapping("/member/username")
	MemberBean findByName(String username);
}
