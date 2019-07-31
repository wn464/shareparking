package com.project.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.Bean.LogBean;
import com.project.Bean.OrderBean;
import com.project.Bean.PageBean;




/**
 * 日志业务
 * @author x1c
 *
 */
public interface ILogService {

	public int insertLog(String username,String message);
	
	public List<LogBean> selectLog();

}
