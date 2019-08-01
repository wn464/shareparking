package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.project.Bean.CarBean;

public interface CarDao {
	/**
	 * 添加车位
	 * @param car
	 * @return
	 */
	@Insert("insert into car (m_id,carnumber,name) values(#{car.m_id.id},#{car.carnumber},#{car.name})")
int addcar(@Param("car")CarBean car);
/**
 * 查询车位
 * @param memid
 * @return
 */
	@Select("select * from car where m_id=#{memid}")
	@Results({
			@Result(property = "id",column = "id"),
			@Result(property="m_id",column = "m-id",one=@One(select="com.project.dao.IMemberDao.findById")),
			@Result(property="carnumber",column = "carnumber"),
			@Result(property="name",column = "name"),
	})
		
List<CarBean> findcarbymid(int memid);
}
