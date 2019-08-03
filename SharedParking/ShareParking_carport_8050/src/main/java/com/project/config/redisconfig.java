package com.project.config;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置redis类，
 * 配置key的生成策略
 *开启缓存
 *默认使用类的权限定类名来作为key
 * @author lenovo
 *
 */
//@Configuration
//@EnableCaching //开启缓存
public class redisconfig extends CachingConfigurerSupport {
@Override
//@Bean
public KeyGenerator keyGenerator() {
	
	return new KeyGenerator() {
		@Override
		public Object generate(Object target, Method method, Object... params) {
			StringBuilder sb=new StringBuilder();
			//获取类类名
			sb.append(target.getClass().getName());
			//获取方法名
			sb.append(method.getName());
			return sb.toString();
		}
	};
}
}
