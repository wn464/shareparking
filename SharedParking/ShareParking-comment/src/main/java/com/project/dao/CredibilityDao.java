package com.project.dao;

import org.apache.ibatis.annotations.Param;

import com.project.Bean.CredibilityBean;

public interface CredibilityDao {
	public CredibilityBean findById(int id);
    public void updateCredibility(@Param("credibility")double credibility,@Param("id")int id);
    public void addCredibility(CredibilityBean bean);
    public void updateCreOrder(@Param("credibility")double credibility,@Param("id")int id,@Param("order")double order);
}
