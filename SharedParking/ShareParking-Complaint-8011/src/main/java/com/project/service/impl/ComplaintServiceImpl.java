package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Bean.ComplaintBean;
import com.project.Bean.CredibilityBean;
import com.project.Bean.MemberBean;
import com.project.Bean.PageBean;
import com.project.dao.ComplaintDao;
import com.project.dao.CredibilityDao;
import com.project.dao.IOrderDao;
import com.project.service.IComplaintService;

@Service
public class ComplaintServiceImpl implements IComplaintService{
	@Autowired
	private ComplaintDao dao;
	@Autowired
    private IOrderDao dao1;
    @Autowired
    private CredibilityDao dao2;
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
		ComplaintBean bean3 = dao.findById(id);
		MemberBean mem_y_id = bean3.getMem_y_id();
		int ordersum = dao1.selectOrderNumberByMem2(mem_y_id.getId());
		int accusesum = dao.findCreNum(mem_y_id.getId());
		double order = ordersum;
		double accuse = accusesum;
		Double credibility = (double) (accuse/order);
		dao2.updateCredibility(credibility, mem_y_id.getId());
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