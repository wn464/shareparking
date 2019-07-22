package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	@Insert("insert into imgs (image1,image2,image) values (#{images.image1},#{images.image2},#{images.image})")
int addimages(@Param("images")ImagesBean images);
/**
 * 查询图片
 * @param id
 * @return
 */
@Select("select *from imgs where id=#{id}")
ImagesBean findimagesbyid(int id);
}
