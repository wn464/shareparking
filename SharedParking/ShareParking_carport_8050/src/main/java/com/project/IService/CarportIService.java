package com.project.IService;

import java.util.List;

import com.project.Bean.CarportBean;
import com.project.Bean.PageBean;

public interface CarportIService {
	/***
	 * 通过车位所属人查询车位
	 * @param mid
	 * @return
	 */
List<CarportBean> findcarportbymid(String mid,String address,String key);
/**
 * 通过车位id查询车位
 * @param cid
 * @return
 */
CarportBean findcarportbycid(int cid);
/**
 * 通过车位编号查询车位
 * @param carportnumber
 * @return
 */
CarportBean findcarportbynumber(String carportnumber);
/**
 * 通过坐标查询范围内车位
 * @param coordinate_x
 * @param coordinate_y
 * @return
 */
List<CarportBean> findcarportbycoordinate(double coordinate_x,double coordinate_y,String key);
/**
 * 查询今日时间之前可租的车位
 * @param time
 * @return
 */
List<CarportBean> findcarportbytime(String time);
/**
 * 查询可租车位，
 * @param page
 * @param size
 * @return
 */
PageBean findcarportbykezu(int page,int size);
/**
 * 查询不可租车位
 * @param page
 * @param size
 * @return
 */
PageBean findcarportbyunkezu(int page,int size);
/**
 * 通过车位id修改车位的状态位可租
 * @return
 */
boolean updatecarportstatusy(int cid);
/**
 * 通过车位id修改车位为不可租
 * @param cid
 * @return
 */
boolean updatecarportstatusf(int cid);
/**
 * 通过车位id修改车位的审核状态
 * @param cid
 * @return
 */
boolean updatecarauditstatus(int cid,int audit);
/**
 * 添加车位
 * @param carport
 * @return
 */
boolean addcarport(CarportBean carport);
/**
 * 查询今日新增车位
 * @param page
 * @param size
 * @return
 */
PageBean findcarportbyday(int page,int size);
/**
 * 查询所有未审核的车辆
 * @return
 */
List<CarportBean> findcarportbyauditstatus();
/**
 * 修改已有车位再出租
 * @param carport
 * @return
 */
boolean updatacarport(CarportBean carport);
/***
 * 通过用户id查询该用户所有车位
 * @param memid
 * @return
 */
List<CarportBean> findcarportbymemid(int memid);
}
