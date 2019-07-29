package com.project.sso.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.project.sso.interfaces.ISSOClientHandler;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//https://blog.csdn.net/dunegao/article/details/90720241
public class AuthFilter extends ZuulFilter{

	@Autowired
	private ISSOClientHandler ssoClientServer;
	//验证交给过滤器
	@Override
	//是否过滤，ture过滤
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		//过滤判断是否需要验证
		
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
        //过滤规则：cookie有令牌且存在于Redis，或者访问的是登录页面、登录请求则放行
        if (url.contains("sso-server/sso/loginPage") || url.contains("sso-server/sso/login") || (!StringUtils.isEmpty(accessToken) && ssoFeign.hasKey(accessToken))) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            return null;
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            //重定向到登录页面
            try {
                response.sendRedirect("http://localhost:10010/sso-server/sso/loginPage?url=" + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
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
