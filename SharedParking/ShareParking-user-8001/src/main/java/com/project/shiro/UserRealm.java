package com.project.shiro;

import com.project.Bean.UserBean;
import com.project.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
//        //获取用户名
//        Object username = principalCollection.getPrimaryPrincipal();
//        Set<String> set = new HashSet<>();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取前台登录的用户名
        String username = authenticationToken.getPrincipal().toString();

        UserBean bean = service.findByUserName(username);
        SimpleAuthenticationInfo info = null;
        if(bean!=null){
            //通过用户名加密
            ByteSource bytes = ByteSource.Util.bytes(username);

            info = new SimpleAuthenticationInfo(bean.getUsername(),bean.getPassword(),bytes,getName());
        }
        return info;
    }
}
