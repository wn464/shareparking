package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class SSOServerHandler {

	
	
	//登录
	@PostMapping("/member/login")
	public boolean memberLogin(String username , String password) {
		//shiro登录
		//成功返回true，失败返回false
		return true;
	}
	@PostMapping("/user/login")
	public boolean userLogin(String username , String password) {
		return true;
	}
	//验证
	@GetMapping("/token")
	public String tokenCheck(String token,String url) {
		//token验证
		//权限验证
		return "";
	}
	//登出
	@GetMapping("/logout")
	public String logout() {
		//清除当前用户的所有缓存
		return "";
	}
}
