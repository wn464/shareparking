package com.project.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.Bean.MemberBean;

@FeignClient(name = "park-member")
public interface IMemberService {

	@RequestMapping(value = "/member/username",method =RequestMethod.GET )
	MemberBean findByName(@RequestParam("username")String username);
}
