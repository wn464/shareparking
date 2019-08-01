package com.project.sso.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
//https://blog.csdn.net/dunegao/article/details/90720241
@Component
public class AuthFilter extends ZuulFilter{

	@Autowired
	private RedisTemplate redisTemplate;
	PathMatcher pathmatcher = new AntPathMatcher();
	//验证交给过滤器
	@Override
	//是否过滤，ture过滤
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		//过滤判断是否需要验证
	  RequestContext ctx = RequestContext.getCurrentContext();
      HttpServletRequest request = ctx.getRequest();
      HttpServletResponse response = ctx.getResponse();
      request.getSession().setAttribute("userid", 1);
      request.getSession().setAttribute("username", "ss");
      request.getSession().setAttribute("memberid", 2);
      request.getSession().setAttribute("membername", "log");
      //访问路径
      System.out.println(request.getSession().getId());
      //String url = request.getRequestURL().toString();
      String uri = request.getRequestURI().toString();
      System.out.println(uri);
      
      
      //CORS实现跨域
      //https://www.cnblogs.com/lazyInsects/p/8110758.html
      HttpServletResponse res =response;  
      String origin = request.getHeader("Origin");
      if(origin == null) {
          origin = request.getHeader("Referer");
      }
     
      System.out.println( request.getMethod());
      System.out.println(origin);
	  res.setContentType("text/html;charset=UTF-8");  
	  res.setHeader("Access-Control-Allow-Origin", origin);  
	  res.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");  
	  res.setHeader("Access-Control-Max-Age", "30");  
	  res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, userId, token, Accept, Accept-Encoding, Accept-Language, Access-Control-Request-Method, Connection, Host, User-Agent, Cache-Control, OPTIONS");  
	  res.setHeader("Access-Control-Allow-Credentials", "true");  
	  res.setHeader("XDomainRequestAllowed","1");  
	  Cookie[] cookies = request.getCookies();
      if(null != cookies){
          for (Cookie cookie : cookies) {
              System.out.println(cookie.getName()+":"+cookie.getValue());
          }
      }
      if(cookies==null) {
    	  System.out.println("cookie为空");
      }
      //复杂请求直接放行
      if( request.getMethod().equals("OPTIONS")) {
    	  ctx.setSendZuulResponse(false);//不再路由
    	  ctx.setResponseStatusCode(200);//设置状态码200
    	  return false;//不过滤
      }
	  return false;
	}

	//过滤器的具体逻辑
	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		//判断是否登录，以及放行
		//查询cookie是否存有数据
		//查询request是否存有数据
		//验证失败保存请求地址
		
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        //访问路径
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI().toString();
        //判断是否放行
        String filterUri=null;
        List<String> filterRole=new ArrayList<String>();
        List<String> filterPermission=new ArrayList<String>();
        Map<String, String> urimap = new LinkedHashMap<String, String>();
        
        urimap.put("/sso/user/login", "anno");
        urimap.put("/sso/user/logout", "anno");
        urimap.put("/sso/unlogin", "anno");
        urimap.put("/sso/unrole", "anno");
        urimap.put("/sso/unpremission", "anno");
        urimap.put("/sso/member/login", "anno");
        urimap.put("/sso/member/logout", "anno");
//      urimap.put("/getusr", "role:user,role:member");
        urimap.put("/**", "");
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
        	ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
        	return null;
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
//        filterPermission =urimap.get(filterUri);
//        if(filterPermission.equals("anno")) {
//        	//不需要登录，放行
//        	return null;
//        }
        if(arry.length==1 && arry[0].equals("anno")) {
        	ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            return null;
        }
        
        //判断是否登录
        //从cookie里面取值（Zuul丢失Cookie的解决方案：https://blog.csdn.net/lindan1984/article/details/79308396）
        String accessToken = request.getParameter("accessToken");
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                }
            }
        }
        if(accessToken==null) {
        	//跳转登录
        	ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            //重定向到登录页面
            try {
            	request.getSession().setAttribute("url", url);
            	response.sendRedirect("http://myzuul.com:9500/sso/unlogin");
            	//判断是web还是app
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        //判断redis是否有效
        Object obj = redisTemplate.opsForValue().get(accessToken);
        if(obj==null) {
        	//登录失效
        	ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            //重定向到登录页面
            try {
            	//判断是web还是app
            	request.getSession().setAttribute("url", url);
            	response.sendRedirect("http://myzuul.com:9500/sso/unlogin");
            	
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        //获取redis参数
        Map<String, String> map = (Map<String, String>) obj;
        
        
        
//        //判断是否拥有角色
//        if(filterPermission.contains("role:")) {
//        	filterPermission.replace("role:", "");
//        }
        if(filterRole.size()!=0) {
        	String role = map.get("role");
        	for (String string : filterRole) {
				if(!string.equals(role)) {
					//提示权限不足
					//登录失效
		        	ctx.setSendZuulResponse(false);
		            ctx.setResponseStatusCode(401);
		            //重定向到登录页面
		            try {
		            	//跳转无角色页面
		            	response.sendRedirect("http://myzuul:9500/sso/unrole");
		            	
		            } catch (IOException  e) {
		                e.printStackTrace();
		            }
		            return null;
				}
			}
        }
        
        
        
        
        //判断是否拥有权限
//        if(filterPermission.contains("perm:")) {
//        	filterPermission.replace("perm:", "");
//        }
        if(filterPermission.size()!=0) {
        	String permission = map.get("permission");
        	for (String string : filterPermission) {
				if(!string.equals(permission)) {
					//提示权限不足
					//登录失效
		        	ctx.setSendZuulResponse(false);
		            ctx.setResponseStatusCode(401);
		            //重定向到登录页面
		            try {
		            	//跳转权限不足的网站
		            	
		            	response.sendRedirect("http://myzuul:9500/sso/unpremission");
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		            return null;
				}
			}
        }
        
        
        
        //剩下为满足条件
        //放行
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        return null;
        
	}

	// Filter 的类型，前置过滤器返回 PRE_TYPE
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}

	//Filter 的顺序，值越小越先执行
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
	}

 

}
