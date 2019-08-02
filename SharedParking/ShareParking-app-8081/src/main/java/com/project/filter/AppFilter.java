package com.project.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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


@WebFilter(urlPatterns = "/*")
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
		        urimap.put("/html/reg.html", "anno");
		        urimap.put("/html/forget_password.html", "anno");
//		      urimap.put("/getusr", "role:user,role:member");
		        urimap.put("/html/**", "");
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
		        //页面不缓存
		        if(pathmatcher.match("/**/*.html", uri)) {
		        	System.out.println("html页面设置不缓存");
		        	//resp.setHeader:发送一个报头，告诉浏览器当前页面不进行缓存，每次访问的时间必须从服务器上读取最新的数据
					//no-cache：客户端每次请求时必须向服务器发送
					//must-revalidate:作用与no-cache相同，但更严谨
					//no-store:缓存将不存在response,包括header和body。
					resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
					// 本地无缓存，自动刷新页面
					resp.setHeader("Pragma", "no-cache"); 
					// Expires实体报头域给出响应过期的日期和时间，小于等于0表示当前页面立即过期，
					// 为了让浏览器不要缓存页面，也可以利用Expires实体报关域，设置为0
					resp.setDateHeader("Expires", 0); 
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
		        	chain.doFilter(request, response);
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
		            	resp.sendRedirect("http://myzuul.com:8082/html/login.html?url="+url);
		            	//判断是web还是app
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		            
		            return;
		        }
		        
		        //判断redis是否有效
		        Object obj = redisTemplate.opsForValue().get(accessToken);
		        System.out.println(obj);
		        if(obj==null) {
		        	//登录失效
		        	
		            //重定向到登录页面
		            try {
		            	//判断是web还是app
		            	resp.sendRedirect("http://myzuul.com:8082/html/login.html?url="+url);
		            	
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
