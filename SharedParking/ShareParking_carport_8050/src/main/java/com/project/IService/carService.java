package com.project.IService;



import java.util.List;

import com.project.Bean.CarBean;


public interface carService {
	/**
	 * 添加车辆
	 * @param car
	 * @return
	 */
boolean addcar(CarBean car); 
/***
 * @param memid
 * @return
 */
List<CarBean> findcars(int memid);
}
