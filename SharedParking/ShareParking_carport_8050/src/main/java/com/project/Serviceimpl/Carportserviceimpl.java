package com.project.Serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.Bean.CarportBean;
import com.project.Bean.MarkBean;
import com.project.Bean.PageBean;
import com.project.IService.CarportIService;
import com.project.dao.CarportDao;
import com.project.dao.ImagesDao;

@Service
public class Carportserviceimpl  implements CarportIService{
	@Autowired
private CarportDao dao;
	@Autowired
	private ImagesDao idao;
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbymid")
	public CarportBean findcarportbymid(int mid) {
		CarportBean  carport=new CarportBean();
		carport=dao.findcarportbyMID(mid);
		return carport;
	}
	
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbycid")
	public CarportBean findcarportbycid(int cid) {
		CarportBean carport =dao.findcarportbycid(cid);
		return carport;
	}
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbynumber")
	public CarportBean findcarportbynumber(int carportnumber) {
		CarportBean carport=dao.findcarportbycarportnumber(carportnumber);
		return carport;
	}
	/***
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbycoordinate")
	public List<CarportBean> findcarportbycoordinate(double coordinate_x, double coordinate_y) {
		List<CarportBean> carports=dao.findcarportbycoordinate(coordinate_x, coordinate_y);
		return carports;
	}
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbykezu")
	public PageBean findcarportbykezu(int page, int size) {
		PageBean pagebean=new PageBean();
		int totalsize=dao.findcarportnumber(10);
		int totalpage=(totalsize%size)==0?totalsize/size:(totalsize/size)+1;
		List<CarportBean> carports=dao.findcarportbyStatus(10, (page-1)*size, size);
		pagebean.setList(carports);
		pagebean.setPage(page);
		pagebean.setSize(size);
		pagebean.setTotalNumber(totalsize);
		pagebean.setTotalPage(totalpage);
		return pagebean;
	}
	@Override
	@Cacheable("findcarportbyunkezu")
	public PageBean findcarportbyunkezu(int page, int size) {
		PageBean pagebean=new PageBean();
		int totalsize=dao.findcarportnumber(11);
		int totalpage=(totalsize%size)==0?totalsize/size:(totalsize/size)+1;
		List<CarportBean> carports=dao.findcarportbyStatus(11, (page-1)*size, size);
		pagebean.setList(carports);
		pagebean.setPage(page);
		pagebean.setSize(size);
		pagebean.setTotalNumber(totalsize);
		pagebean.setTotalPage(totalpage);
		return pagebean;
	}
	/**
	 * 
	 */
	@Override
	@CacheEvict(value="updatecarportstatusy",allEntries = true)
	public boolean updatecarportstatusy(int cid) {
		CarportBean carport=new CarportBean();
		carport.setId(cid);
		MarkBean mark=new MarkBean();
		mark.setId(10);
		carport.setStatus(mark);
		int s=dao.updatecarport(carport);
		if(s>0) {
			return true;
		}
		return false;
	}
	/***
	 * 
	 */
	@Override
	@CacheEvict(value="updatecarportstatusf",allEntries = true)
	public boolean updatecarportstatusf(int cid) {
		CarportBean carport=new CarportBean();
		carport.setId(cid);
		MarkBean mark=new MarkBean();
		mark.setId(11);
		carport.setStatus(mark);
		int s=dao.updatecarport(carport);
		if(s>0) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 */
	@Override
	@CacheEvict(value="updatecarauditstatus",allEntries = true)
	public boolean updatecarauditstatus(int cid) {
		CarportBean carport=new CarportBean();
		carport.setId(cid);
		MarkBean mark=new MarkBean();
		mark.setId(9);
		carport.setStatus(mark);
		int s=dao.updatecarport(carport);
		if(s>0) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
	public boolean addcarport(CarportBean carport) {
		idao.addimages(carport.getImgs_id());
		int s=dao.addcarport(carport);
		if(s>0) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbyday")
	public PageBean findcarportbyday(int page, int size) {
		PageBean pagebean=new PageBean();
		SimpleDateFormat data=new SimpleDateFormat("yyy-mm-dd 23:00:00");
		Date da=new Date();
		String a=data.format(da);
		int totalsize=dao.findcarportbydaynumber(a);
		int totalpage=(totalsize%size)==0?totalsize/size:(totalsize/size)+1;
		List<CarportBean> carports=dao.findcarportdaybytime(a, (page-1)*size,size);
		pagebean.setList(carports);
		pagebean.setPage(page);
		pagebean.setTotalNumber(totalsize);
		pagebean.setTotalPage(totalpage);
		pagebean.setSize(size);
		return pagebean;
	}

	@Override
	@Cacheable(value="findcarportbytime")
	public List<CarportBean> findcarportbytime(String time) {
		List<CarportBean> carports=dao.findcarportbytime(time);
		return carports;
	}

}
