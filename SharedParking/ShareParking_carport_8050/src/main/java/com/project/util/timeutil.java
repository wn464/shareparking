package com.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.Bean.CarportBean;
import com.project.Bean.MarkBean;
import com.project.dao.CarportDao;

@Component
public class timeutil {
private static final Logger logger =LoggerFactory.getLogger(timeutil.class);
private static final SimpleDateFormat datafromat=new SimpleDateFormat("yyy-mm-dd hh:mm:ss");
@Autowired
private CarportDao dao;

@Scheduled(fixedRate = 1000*60*5)
public void report() {
	System.out.println("开始修改车位状态");
	Date te =new Date();
	String a=datafromat.format(te);
	List<CarportBean> carports=dao.findcarportbytime(a);
	for (CarportBean carportBean : carports) {
		MarkBean mark =new MarkBean();
		mark.setId(11);
		carportBean.setStatus(mark);
		dao.updatecarport(carportBean);
	}
	
}
}
