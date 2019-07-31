package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		System.out.println(username);
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
	
	/*
	 * 修改个人信息
	 * 1：修改成功
	 * 2：修改失败
	 */
	@PutMapping("/member/updatemyself")
	public int updateMySelf(String address,String job,String phone) {
		
		int mySelf = service.updateMySelf(address, job,1, phone);
		if(mySelf!=0) {
			return 1;
		}
		return 2;
				
	}
	
	/*
	 * 查看个人信息
	 */
	@GetMapping("/member/findbyid")
	public MemberBean findById() {
		
		MemberBean bean = service.findById(1);
		System.out.println(bean);
		return bean;
	}
}
