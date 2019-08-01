package com.project.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

//@Component
public class AppFilter implements Filter {
	
	PathMatcher pathmatcher = new AntPathMatcher();
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				//判断是否登录，以及放行
				//查询cookie是否存有数据
				//查询request是否存有数据
				//验证失败保存请求地址
				
		        HttpServletRequest req = (HttpServletRequest) request;
		        HttpServletResponse resp = (HttpServletResponse) response;
		        //访问路径
		        String url = req.getRequestURL().toString();
		        String uri = req.getRequestURI().toString();
		        System.out.println(url);
		        //判断是否放行
		        String filterUri=null;
		        List<String> filterRole=new ArrayList<String>();
		        List<String> filterPermission=new ArrayList<String>();
		        Map<String, String> urimap = new LinkedHashMap<String, String>();
		        
		        urimap.put("/html/login.html", "anno");
		        urimap.put("/sso/user/logout", "anno");
		        urimap.put("/sso/unlogin", "anno");
		        urimap.put("/sso/unrole", "anno");
		        urimap.put("/sso/unpremission", "anno");
		        urimap.put("/sso/member/login", "anno");
		        urimap.put("/sso/member/logout", "anno");
//		      urimap.put("/getusr", "role:user,role:member");
		        urimap.put("/**", "anno");
		        Set<String> set =urimap.keySet();
		        for (String string : set) {
		        	if(pathmatcher.match(string, uri)) {
		        		filterUri = string;
		        		break;
		        	}
				}
		        if(filterUri==null) {
		        	//不放行
		        	//不受理页面
		        	
		        	return;
		        }
		        
		        //判断是否需要登录
		         String[] arry = urimap.get(filterUri).split(",");
		         for (String string : arry) {
					System.out.println(string);
				}
		         for (String string : arry) {
					if(string.contains("role:")) {
						filterRole.add(string.replace("role:", ""));
					}
					if(string.contains("permission:")) {
						filterPermission.add(string.replace("permission:", ""));
					}
				}
//		        filterPermission =urimap.get(filterUri);
//		        if(filterPermission.equals("anno")) {
//		        	//不需要登录，放行
//		        	return null;
//		        }
		        if(arry.length==1 && arry[0].equals("anno")) {
		        	
		            return ;
		        }
		        
		        //判断是否登录
		        //从cookie里面取值（Zuul丢失Cookie的解决方案：https://blog.csdn.net/lindan1984/article/details/79308396）
		        String accessToken = req.getParameter("accessToken");
		        Cookie[] cookies = req.getCookies();
		        if(null != cookies){
		            for (Cookie cookie : cookies) {
		                if ("accessToken".equals(cookie.getName())) {
		                    accessToken = cookie.getValue();
		                }
		            }
		        }
		        if(accessToken==null) {
		        	//跳转登录
		        	
		            //重定向到登录页面
		            try {
		            	req.getSession().setAttribute("url", url);
		            	resp.sendRedirect("http://myzuul.com/html/login.htm?url="+url);
		            	//判断是web还是app
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		            return;
		        }
		        
		        //判断redis是否有效
		        Object obj = redisTemplate.opsForValue().get(accessToken);
		        if(obj==null) {
		        	//登录失效
		        	
		            //重定向到登录页面
		            try {
		            	//判断是web还是app
		            	req.getSession().setAttribute("url", url);
		            	resp.sendRedirect("http://myzuul.com/html/login.com?url="+url);
		            	
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		            return;
		        }
		        
		        //获取redis参数
		        Map<String, String> map = (Map<String, String>) obj;
		        
		        
		        
//		        //判断是否拥有角色
//		        if(filterPermission.contains("role:")) {
//		        	filterPermission.replace("role:", "");
//		        }
		        if(filterRole.size()!=0) {
		        	String role = map.get("role");
		        	for (String string : filterRole) {
						if(!string.equals(role)) {
							//提示权限不足
							//登录失效
				        	
				            //重定向到登录页面
				            try {
				            	//跳转无角色页面
				            	resp.sendRedirect("http://myzuul:9500/sso/unrole");
				            	
				            } catch (IOException  e) {
				                e.printStackTrace();
				            }
				            return;
						}
					}
		        }
		        
		        
		        
		        
		        //判断是否拥有权限
//		        if(filterPermission.contains("perm:")) {
//		        	filterPermission.replace("perm:", "");
//		        }
		        if(filterPermission.size()!=0) {
		        	String permission = map.get("permission");
		        	for (String string : filterPermission) {
						if(!string.equals(permission)) {
							//提示权限不足
							//登录失效
				        	
				            //重定向到登录页面
				            try {
				            	//跳转权限不足的网站
				            	
				            	resp.sendRedirect("http://myzuul:9500/sso/unpremission");
				            } catch (IOException e) {
				                e.printStackTrace();
				            }
				            return;
						}
					}
		        }
		        
		        
		        
		        //剩下为满足条件
		        //放行
		        System.out.println("放行");
		        chain.doFilter(request, response);
		        return ;
		
		
	}
	

}
