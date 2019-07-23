package com.project.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
    设置 任意一个角色 即可访问
 */
public class OneOfRoleAuthorzationFilter extends AuthorizationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		Subject subject = getSubject(request, response);
		String url = ((HttpServletRequest)request).getRequestURI();
        // If the subject isn't identified, redirect to login URL
		 
        if (subject.getPrincipal() == null) {
//            saveRequestAndRedirectToLogin(request, response);
            if (url.contains("/admin/")) {
             	WebUtils.issueRedirect(request, response, "/admin/login.html");
             	 return false;
             }
        } else {
            // If subject is known but not authorized, redirect to the unauthorized URL if there is one
            // If no unauthorized URL is specified, just return an unauthorized HTTP status code
            String unauthorizedUrl = getUnauthorizedUrl();
            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
            if (url.contains("/admin/")) {
            	WebUtils.issueRedirect(request, response, "/admin/login.html");
            } else if(StringUtils.hasText(unauthorizedUrl)){
            	WebUtils.issueRedirect(request, response, unauthorizedUrl);
            }
            else {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
	}
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		//获取到subject对象
		
		Subject subject = getSubject(request, response);  
        String[] rolesArray = (String[]) mappedValue;  
        //角色没有配置，访问失败  
        if (rolesArray == null || rolesArray.length == 0) { 
            return false;  
        }  
        for (int i = 0; i < rolesArray.length; i++) {  
             //若当前用户是rolesArray中的任何一个，则有权限访问  
            if (subject.hasRole(rolesArray[i])) {
                return true;  
            }  
        }     
		return false;
	}

	

}
