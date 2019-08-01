package com.project.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;

import com.project.Bean.MemberBean;
import com.project.Bean.UserBean;
import com.project.interfaces.IMemberService;
import com.project.interfaces.IUserService;
@RestController
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
		System.out.println(username);
		MemberBean member = memberservice.findByName(username);
		System.out.println(member);
		if(member==null) {
			return false;
		}
		if(!member.getPassword().equals(password)) {
			return false;
		}
		//设置session
        HttpSession session = request.getSession();
        session.setAttribute("memberid", member.getId());
        session.setAttribute("membername", member.getUsername());
		createToken(member.getId(), username, MEMBER, "",request,response);
		return true;
	}
	@PostMapping("/user/login")
	public boolean userLogin(String username , String password,HttpServletRequest request,HttpServletResponse response) {
		UserBean user = userservice.findByName(username);
		System.out.println(user);
		System.out.println(username);
		if(user==null) {
			return false;
		}
		if(!user.getPassword().equals(password)) {
			return false;
		}
		//设置session
        HttpSession session = request.getSession();
        session.setAttribute("userid", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setMaxInactiveInterval(60*30);
		createToken(user.getId(), username, USER, user.getAuthority().getName(),request,response);
		return true;
	}
	@GetMapping("/unlogin")
	public String unlogin() {
		return "未登录";
	}
	@GetMapping("/unrole")
	public String unrole() {
		return "没有角色";
	}
	@GetMapping("/unpremission")
	public String unpremission() {
		return "没有登录";
	}
	//登出
	@GetMapping("/logout")
	public void userLogout(HttpServletRequest request,HttpServletResponse response) {
		//清除redis
		
		Map<String, String > map = getRedis(getToken(request, response));
		clearToken(getToken(request, response));
		if(map!=null) {
			String role = map.get("role");
			switch (role) {
			case USER:
				try {
					response.sendRedirect("http://myzuul.com:8083");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case MEMBER:
				try {
					response.sendRedirect("http://myzuul.com:8082");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}
			
		}
		
		//跳转登录
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
			return ;
		}
		Object obj = redisTemplate.opsForValue().get(token);
		if(obj==null) {
			return;
		}
		
		//删除信息
		System.out.println("登出");
		redisTemplate.delete(token);
		//返回网页
	}
	
	public Map<String, String> getRedis(String token){
		//获取角色，进行跳转
		Object obj = redisTemplate.opsForValue().get(token);
		if(obj==null) {
			return null;
		}
		Map<String, String> map = (Map<String, String>) obj;
		return map;
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
