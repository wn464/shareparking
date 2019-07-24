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
List<CarportBean> findcarportbymid(int mid,String address);
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
List<CarportBean> findcarportbycoordinate(double coordinate_x,double coordinate_y);
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
boolean updatecarauditstatus(int cid);
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
}
