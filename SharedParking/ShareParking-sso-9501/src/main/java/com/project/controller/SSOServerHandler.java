package com.project.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.project.Bean.MemberBean;
import com.project.Bean.UserBean;
import com.project.interfaces.IMemberService;
import com.project.interfaces.IUserService;

public class SSOServerHandler {
	
	@Autowired
	IMemberService memberservice;
	@Autowired
	IUserService userservice;
	
	@Autowired
	private RedisTemplate redisTemplate;
	final static String USER = "user";
	final static String MEMBER = "member";
	final static String TOKEN = "accessToken";
	//登录
	@PostMapping("/member/login")
	public boolean memberLogin(String username , String password ,HttpServletRequest request,HttpServletResponse response) {
		//shiro登录
		//成功返回true，失败返回false
		MemberBean member = memberservice.findByName(username);
		if(member==null) {
			return false;
		}
		if(!member.getPassword().equals(password)) {
			return false;
		}
		createToken(member.getId(), username, USER, "",request,response);
		return true;
	}
	@PostMapping("/user/login")
	public boolean userLogin(String username , String password,HttpServletRequest request,HttpServletResponse response) {
		UserBean user = userservice.findByName(username);
		if(user==null) {
			return false;
		}
		if(!user.getPassword().equals(password)) {
			return false;
		}
		createToken(user.getId(), username, USER, user.getAuthority().getName(),request,response);
		return true;
	}
	
	
	
	//登出
	@GetMapping("/user/logout")
	public String userLogout(HttpServletRequest request,HttpServletResponse response) {
		//清除redis
		clearToken(getToken(request, response));
		//跳转登录
		return "/user/index.html";
	}
	@GetMapping("/member/logout")
	public String memberLogout(HttpServletRequest request,HttpServletResponse response) {
		//清除redis
		clearToken(getToken(request, response));
		//跳转登录
		return "/member/index.html";
	}
	
	public String getToken(HttpServletRequest request,HttpServletResponse response) {
		String token = "";
		if(request.getAttribute(TOKEN)!=null) {
			token = (String) request.getAttribute(TOKEN);
		}
		Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                	token = cookie.getValue();
                }
            }
        };
        
        return token; 
	}
	public void clearToken(String token) {
		if(token=="") {
			return;
		}
		//获取角色，进行跳转
		Map<String, String> map = (Map<String, String>) redisTemplate.opsForValue().get(token);
		String role = map.get("role");
		//删除信息
		redisTemplate.delete(token);
		//返回网页
		
	}
	//生成token
	public void createToken(int id,String username,String role,String permission,HttpServletRequest request,HttpServletResponse response) {
		//生成redis
		String tokenKey = UUID.randomUUID().toString();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", Integer.toString(id));
		map.put("username", username);
		map.put("role", role);
		map.put("permission", permission);
		redisTemplate.opsForValue().set(tokenKey, map, 60*30, TimeUnit.SECONDS);
		Cookie cookie = new Cookie(TOKEN, tokenKey);
        cookie.setMaxAge(60 * 3);
        //设置域
        //cookie.setDomain("huanzi.cn");
        //设置访问路径
        cookie.setPath("/");
        response.addCookie(cookie);
        request.getSession().setAttribute("id", id);
		//生成cookie
	}
}