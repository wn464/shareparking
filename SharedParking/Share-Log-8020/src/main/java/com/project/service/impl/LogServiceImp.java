package com.project.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.Bean.LogBean;

import com.project.dao.ILogDao;

import com.project.service.ILogService;
import com.project.util.CreateTimeUtil;
/**
 * 订单业务
 * @author x1c
 *
 */
@Service
public  class LogServiceImp implements ILogService{

	@Autowired
	private ILogDao logDao;
	
	//查询日志
	@Override
	public List<LogBean> selectLog() {
		List<LogBean> list = logDao.selectLog();
		return list;
	}

	//添加日志
	@Override
	public int insertLog(String username, String message) {
		LogBean logBean = new LogBean();
		logBean.setUsername(username);
		logBean.setMessage(message);
		String time =  CreateTimeUtil.getCreateTime();
		logBean.setTime(time);
		int num = logDao.insertLog(logBean);
		return num;
	}
	

	
}
