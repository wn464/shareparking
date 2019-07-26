package com.project.Serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	@Cacheable(value="findcarportbymid",key="#key")
	public List<CarportBean> findcarportbymid(String mid,String address,String key) {
		List<CarportBean>  carports=dao.findcarportbyMID(mid,address);
		return carports;
	}
	
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbycid",key="#cid")
	public CarportBean findcarportbycid(int cid) {
		CarportBean carport =dao.findcarportbycid(cid);
		return carport;
	}
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbynumber",key="#carportnumber")
	public CarportBean findcarportbynumber(String carportnumber) {
		CarportBean carport=dao.findcarportbycarportnumber(carportnumber);
		return carport;
	}
	/***
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbycoordinate",key="#coordinate_x")
	public List<CarportBean> findcarportbycoordinate(double coordinate_x, double coordinate_y) {
		List<CarportBean> carports=dao.findcarportbycoordinate(coordinate_x, coordinate_y);
		return carports;
	}
	/**
	 * 
	 */
	@Override
	@Cacheable(value="findcarportbykezu",key="#page")
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
	@Cacheable(value="findcarportbyunkezu",key="#page")
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
	@CacheEvict(value= {"*"},allEntries = true)
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
	@CacheEvict(value= {"*"},allEntries = true)
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
	@CacheEvict(value= {"*"},allEntries = true)
	public boolean updatecarauditstatus(int cid,int audit) {
		CarportBean carport=new CarportBean();
		carport.setId(cid);
		if(audit==9) {
			MarkBean status=new MarkBean();
			status.setId(10);
			carport.setStatus(status);
		}
		MarkBean mark=new MarkBean();
		mark.setId(audit);
		carport.setAuditstatus(mark);
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
	@Cacheable(value="findcarportbyday",key="#page")
	public PageBean findcarportbyday(int page, int size) {
		PageBean pagebean=new PageBean();
		SimpleDateFormat data=new SimpleDateFormat("yyy-MM-dd 23:00:00");
		Date da=new Date();
		String a=data.format(da);
		SimpleDateFormat data1=new SimpleDateFormat("yyy-MM-dd 01:00:00");
		String b=data1.format(da);
		int totalsize=dao.findcarportbydaynumber(a,b);
		int totalpage=(totalsize%size)==0?totalsize/size:(totalsize/size)+1;
		List<CarportBean> carports=dao.findcarportdaybytime(a,b, (page-1)*size,size);
		pagebean.setList(carports);
		pagebean.setPage(page);
		pagebean.setTotalNumber(totalsize);
		pagebean.setTotalPage(totalpage);
		pagebean.setSize(size);
		return pagebean;
	}

	@Override
	@Cacheable(value="findcarportbytime",key="#time")
	public List<CarportBean> findcarportbytime(String time) {
		List<CarportBean> carports=dao.findcarportbytime(time);
		return carports;
	}

	@Override
	@Cacheable(value="findcarportbyauditstatus")
	public List<CarportBean> findcarportbyauditstatus(){
		List<CarportBean> carports=	dao.findcarportbyditstatus();
		return carports;
	}

	@Override
	public boolean updatacarport(CarportBean carport) {
		int s=dao.updatecarport(carport);
		if(s>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<CarportBean> findcarportbymemid(int memid) {
		// TODO Auto-generated method stub
		return null;
	}

}
