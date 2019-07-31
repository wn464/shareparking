package com.project.sso.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

//@Component
public class SSOConfig {

	
	//过滤用的map
	public static Map<String, String> map = new LinkedHashMap<String, String>();
	
	public SSOConfig() {
		this.map.put("/**", "anno");
	}
	public SSOConfig(Map<String, String> map) {
		this.map=map;
	}
}
