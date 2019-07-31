package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.objenesis.instantiator.annotations.Instantiator;

import com.project.Bean.ImagesBean;

/**
 * 车位图片持久层
 * @author zxc
 *
 */
public interface ImagesDao {
	/**
	 * 添加图片
	 * @param images
	 * @return
	 */
int addimages(@Param("images")ImagesBean images);
/**
 * 查询图片
 * @param id
 * @return
 */
	@Select("select *from imgs where id=#{id}")
ImagesBean findimagesbyid(@Param("id")int id);
}
