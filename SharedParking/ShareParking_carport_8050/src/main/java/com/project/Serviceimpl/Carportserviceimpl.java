package com.project.Serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Bean.CarportBean;
import com.project.IService.CarportIService;
import com.project.dao.CarportDao;

@Service
public class Carportserviceimpl  implements CarportIService{
	@Autowired
private CarportDao dao;
	@Override
	public CarportBean findcarportbymid(int mid) {
		CarportBean  carport=new CarportBean();
		carport=dao.findcarportbyMID(mid);
		return carport;
	}

}
