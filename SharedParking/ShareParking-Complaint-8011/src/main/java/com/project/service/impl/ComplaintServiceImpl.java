package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Bean.ComplaintBean;
import com.project.Bean.PageBean;
import com.project.dao.ComplaintDao;
import com.project.service.IComplaintService;

@Service
public class ComplaintServiceImpl implements IComplaintService{
	@Autowired
	private ComplaintDao dao;

	@Override
	public void addComplaint(ComplaintBean bean) {
		dao.addComplaint(bean);
		
	}

	@Override
	public PageBean findByStatus(int status, int page, int size) {
		PageBean bean = new PageBean();
		List<ComplaintBean> list = new ArrayList<ComplaintBean>();
		int totalNumber = dao.findComNum(status);
		list=dao.findByStatus(status, (page-1)*size, size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setPage(page);
		bean.setSize(size);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		System.out.println(list);
		return bean;
	}

	@Override
	public void updateComplaint(int id) {
		dao.updateComplaint(id);
		
	}

	@Override
	public void deleteComplaint(int id) {
		dao.deleteComplaint(id);
		
	}

	@Override
	public int findComNum(int status) {
		int i = dao.findComNum(status);
		return i;
	}

	@Override
	public PageBean findByDate(String begintime, String endtime, int page, int size) {
		PageBean bean = new PageBean();
		List<ComplaintBean> list = new ArrayList<ComplaintBean>();
		int totalNumber = dao.findDateNum(begintime, endtime);
		list=dao.findByDate(begintime, endtime,(page-1)*size, size);
		bean.setList(list);
		bean.setTotalNumber(totalNumber);
		bean.setPage(page);
		bean.setSize(size);
		bean.setTotalPage((totalNumber%size==0)?(totalNumber/size):(totalNumber/size+1));
		System.out.println(list);
		return bean;
	}

	@Override
	public int findDateNum(String begintime, String endtime) {
		int i = dao.findDateNum(begintime, endtime);
		return i;
	}
	

}
