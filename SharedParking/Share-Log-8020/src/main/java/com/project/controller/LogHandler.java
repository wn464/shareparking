package com.project.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.Bean.LogBean;
import com.project.service.ILogService;

@RestController
public class LogHandler {

	@Autowired
	private ILogService logService;
	
	
	/*
	 * 添加日志
	 */
	@PostMapping("/log/{username}/{message}")
	public Integer insertLog(@PathVariable("username")String username,@PathVariable("message")String message) {
		System.out.println("日志"+username+"----------"+message);
		int num = logService.insertLog(username, message);
		return num;
	}
	
	/*
	 * 查询日志
	 */
	@GetMapping("/log")
	public List<LogBean> selectLog() {
		System.out.println("日志查询");
		List<LogBean>  list= logService.selectLog();
		return list;
	}
}
