package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.Bean.CarportBean;

/**
 * 停车位接口
 * 
 * @author zxc
 *
 */
public interface CarportDao {
	
	/**
	 * 通过车位编号查询车位
	 * 
	 * @param carportnumber
	 * @return
	 */
	CarportBean findcarportbycarportnumber(@Param("number") String carportnumber);

	/**
	 * 通过车位id查询车位
	 * 
	 * @param cid
	 * @return
	 */
	CarportBean findcarportbycid(@Param("cid") int cid);

	/**
	 * 通过车位所属人查询车位
	 * 
	 * @param mid
	 * @return
	 */
	List<CarportBean> findcarportbyMID(@Param("mid") String mid,@Param("address")String address);

	/**
	 * 通过坐标查询坐标范围内的车位
	 * 
	 * @param coordinate_x
	 * @param coordinate_y
	 * @return
	 */
	List<CarportBean> findcarportbycoordinate(@Param("x") double coordinate_x, @Param("y") double coordinate_y);

	/**
	 * 通过当前时间查询的车位
	 * 
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	List<CarportBean> findcarportbytime(@Param("time")String time);
	/**
	 * 查询某状态下所有的车位
	 * @param status
	 * @return
	 */
int findcarportnumber(int status);
	/**
	 * 通过车位出租状态来查询车位
	 * 
	 * @param status
	 * @param page
	 * @param size
	 * @return 
	 */
	
	 List<CarportBean> findcarportbyStatus(@Param("status")int status,@Param("page")int page,@Param("size")int size);
	 /**
		 * 通过id修改车位信息
		 * 
		 * @param id
		 * @param status
		 * @return
		 */
	int updatecarport(@Param("carport") CarportBean carport);

	/**
	 * 通过价格范围查询车位
	 * 
	 * @param price
	 * @return
	 */
	List<CarportBean> findcarportbyprice(@Param("minprice") double minprice, @Param("maxprice") double maxprice);

	/**
	 * 添加车位
	 * 
	 * @param carportBean
	 * @return
	 */
	int addcarport(@Param("carport") CarportBean carportBean);
	/**
	 * 查询今日新增的车位
	 * @param day
	 * @return
	 */
	List<CarportBean> findcarportdaybytime(@Param("day")String day,@Param("endtime")String endtime,@Param("page")int page,@Param("size")int size);
	/**
	 * 查询今日新增车位的数量
	 * @param day
	 * @return
	 */
	int findcarportbydaynumber(@Param("day")String day,@Param("endtime")String endtime);
	/**
	 * 查询所有未审核的车位
	 * @return
	 */
	List<CarportBean> findcarportbyditstatus();
	/**
	 * 通过用户id查询用户所有车位
	 * @param memid
	 * @return
	 */
	List<CarportBean> findcarportbymemid(@Param("memid")int memid);
}
