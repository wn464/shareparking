package com.project.sso.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
      request.getSession().setAttribute("memberid", 1);
      //访问路径
      System.out.println(request.getSession().getId());
      String url = request.getRequestURL().toString();
      String uri = request.getRequestURI().toString();
      System.out.println(uri);
      
      
      //CORS实现跨域
      //https://www.cnblogs.com/lazyInsects/p/8110758.html
      HttpServletResponse res =response;  
	  res.setContentType("text/html;charset=UTF-8");  
	  res.setHeader("Access-Control-Allow-Origin", "*");  
	  res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
	  res.setHeader("Access-Control-Max-Age", "0");  
	  res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");  
	  res.setHeader("Access-Control-Allow-Credentials", "true");  
	  res.setHeader("XDomainRequestAllowed","1");  
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
        String filterPermission=null;
        Map<String, String> urimap = new LinkedHashMap<String, String>();
        
        
        
        
        Set<String> set =urimap.keySet();
        for (String string : set) {
        	if(pathmatcher.match(string, uri)) {
        		filterUri = string;
        		break;
        	}
		}
        if(filterUri==null) {
        	//不放行
        	
        	
        	return null;
        }
        
        //判断是否需要登录
        filterPermission =urimap.get(filterUri);
        if(filterPermission.equals("anno")) {
        	//不需要登录，放行
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
        }
        
        //判断redis是否有效
        Object obj = redisTemplate.opsForValue().get(accessToken);
        if(obj==null) {
        	//登录失效
        }
        
        //获取redis参数
        Map<String, String> map = (Map<String, String>) obj;
        
        //判断session是否有效
        
        
        
        //判断是否拥有角色
        if(filterPermission.contains("role-")) {
        	filterPermission.replace("role-", "");
        }
        String role = map.get("role");
        
        
        
        //判断是否拥有权限
        if(filterPermission.contains("perm-")) {
        	filterPermission.replace("perm-", "");
        }
        String permission = map.get("permission");
       
        
        
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
