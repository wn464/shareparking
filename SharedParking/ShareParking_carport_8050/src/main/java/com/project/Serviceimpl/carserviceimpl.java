package com.project.Serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.Bean.CarBean;
import com.project.IService.carService;
import com.project.dao.CarDao;
@Service
public class carserviceimpl implements carService {
	@Autowired
private CarDao dao;
	@Override
	@CacheEvict(value= {"findcars"},allEntries = true) 
	public boolean addcar(CarBean car) {
		int s=dao.addcar(car);
		if(s>0) {
			return true;
		}
		return false;
	}

	@Override
	@Cacheable(value="findcars",key="memid") 
	public List<CarBean> findcars(int memid) {
	List<CarBean> cars=new ArrayList<CarBean>();
	cars=dao.findcarbymid(memid);
		return cars;
	}

}
