package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Bean.MemberBean;
import com.project.Bean.MessageBean;
import com.project.Bean.UserBean;
import com.project.service.IMemberService;

@RestController
public class MemberHandler {
	@Autowired
	private IMemberService service;
	

	@GetMapping("/member/username")
	public MemberBean findByName(String username) {
		return service.findByUserName(username);
	}
	
	/*
	 * 查询所有用户
	 */
	@GetMapping("/member/findAll")
	public List<MessageBean> findAll(){
		List<MessageBean> list = service.findAll();

		return list;
	}
	/*
	 * 模糊查询
	 */
	@GetMapping("/member/findMohu")
	public List<MessageBean> findByMohu(String name) {
		List<MessageBean> list = service.findByMohu(name);
		return list;
	}
	
}
