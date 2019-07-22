package com.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.Bean.MarkBean;



/**
 * 码表实体类
 * @author zxc
 *
 */
public interface Markdao {
	@Select("select * from mark where id=#{id}")
MarkBean findmarkbyid(@Param("id")int id);
}
