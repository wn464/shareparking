package com.project.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.Bean.CarportBean;


/**
 * 停车位接口
 * @author zxc
 *
 */
public interface CarportDao {
	/**
	 * 通过车位id查询车位
	 * @param cid
	 * @return
	 */
	CarportBean findcarportbycid(@Param("cid")int cid);
	/**
	 * 通过车位所属人查询车位
	 * @param mid
	 * @return
	 */
	CarportBean findcarportbyMID(@Param("mid")int mid);
	/**
	 * 通过坐标查询坐标范围内的车位
	 * @param coordinate_x
	 * @param coordinate_y
	 * @return
	 */
	List<CarportBean> findcarportbycoordinate(@Param("x")double coordinate_x,@Param("y")double coordinate_y,@Param("page")int page,@Param("size")int size);
	/**
	 * 通过出租车为时间查找时间内的车位
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	List<CarportBean> findcarportbytime(@Param("btime")Date begintime,@Param("etime")Date endtime,@Param("page")int page,@Param("size")int size);
	/**
	 * 通过车位出租状态来查询车位
	 * @param status
	 * @param page
	 * @param size
	 * @return
	 */
	List<CarportBean> findcarportbyStatus(@Param("status")int status,@Param("page")int page,@Param("size")int size);
	/**
	 * 通过id修改车位出租的状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updatecarport(@Param("id")int id,@Param("status")int status);
	/**
	 * 通过id修改审核的状态
	 * @param id
	 * @param auditstatus
	 * @return
	 */
	int updatecarportauditstatus(int id,int auditstatus);
	/**
	 * 通过价格范围查询车位
	 * @param price
	 * @return
	 */
	List<CarportBean> findcarportbyprice(@Param("minprice")double minprice,@Param("maxprice")double maxprice);
	/**
	 * 添加车位
	 * @param carportBean
	 * @return
	 */
	int addcarport(CarportBean carportBean) ;
}
