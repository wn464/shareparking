package com.project.dao;

import java.util.List;

import com.project.Bean.LogBean;

public interface ILogDao {

	public int insertLog(LogBean logBean);
	
	public List<LogBean> selectLog();
}
