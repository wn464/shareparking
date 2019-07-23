package com.project.config;

import com.project.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        //认证失败跳转地址
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        Map<String,String> fmap = new LinkedHashMap<>();
        fmap.put("/css/**","anon");
        fmap.put("/js/**","anon");
        fmap.put("/img/**","anon");

        //只有 这几个角色才能访问
        fmap.put("/admin/**", "oneOfRole[admin,superadmin]");
        fmap.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fmap);
        return shiroFilterFactoryBean;
    }

    /*
        安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealms(realms());
        return defaultWebSecurityManager;
    }

    /*
        Realm
        生成Realm  这么写 是因为可能存在多个Realm
        可以直接用list集合 add 添加 然后统一注入安全管理器
     */
    @Bean
    public UserRealm userRealm(){
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(getHashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public Collection<Realm> realms(){
        Collection<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        return realms;
    }


    /*
        MD5加密
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

}
