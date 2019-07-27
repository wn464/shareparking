package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.Bean.ComplaintBean;
import com.project.Bean.CredibilityBean;
import com.project.Bean.MemberBean;
import com.project.Bean.OrderBean;
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
	@CacheEvict(value="*",allEntries = true)
	public void addComplaint(ComplaintBean bean) {
		dao.addComplaint(bean);
		
	}

	@Override
	@Cacheable(value = "findByStatus",key = "#str")
	public PageBean findByStatus(int status, int page, int size,String str) {
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
	@CacheEvict(value="*",allEntries = true)
	public void updateComplaint(int id,int type) {
		dao.updateComplaint(id);
		dao.updateType(id, type);
		ComplaintBean bean3 = dao.findById(id);
//		*******************************************
		int c_mem_y_id = bean3.getMem_y_id().getId();
		OrderBean o_id = bean3.getO_id();
		int mem_j_id = o_id.getMemberBean1().getId();
		int mem_y_id = o_id.getMemberBean2().getId();
		int ordersum=0;
		int accusesum=0;
		if(c_mem_y_id==mem_j_id) {
			if(type==16) {
			ordersum = dao1.selectOrderNumberByMem2(mem_j_id);
			int ordersum1 = dao1.selectOrderNumberByMem1(mem_j_id);
			ordersum = ordersum+ordersum1;
			accusesum = dao.findCreNum(mem_j_id);
			double order = ordersum;
			double accuse = accusesum;
			Double credibility = 1-(double) (accuse/order);
			dao2.updateCredibility(credibility, mem_j_id);
			}
		}else {
			if(type==16) {
			ordersum = dao1.selectOrderNumberByMem2(mem_y_id);
			int ordersum1 = dao1.selectOrderNumberByMem1(mem_y_id);
			ordersum = ordersum+ordersum1;
			accusesum = dao.findCreNum(mem_y_id);
			double order = ordersum;
			double accuse = accusesum;
			Double credibility = 1-(double) (accuse/order);
			dao2.updateCredibility(credibility, mem_y_id);
			}
		}
//		*******************************************
//		MemberBean mem_y_id = bean3.getMem_y_id();
//		int ordersum = dao1.selectOrderNumberByMem2(mem_y_id.getId());
//		int accusesum = dao.findCreNum(mem_y_id.getId());
//		double order = ordersum;
//		double accuse = accusesum;
//		Double credibility = (double) (accuse/order);
//		dao2.updateCredibility(credibility, mem_y_id.getId());
	}

	@Override
	@CacheEvict(value="*",allEntries = true)
	public void deleteComplaint(int id) {
		dao.deleteComplaint(id);
		
	}

	@Override
	@Cacheable(value = "findComNum",key = "#status")
	public int findComNum(int status) {
		int i = dao.findComNum(status);
		return i;
	}

	@Override
	@Cacheable(value = "findByDate",key = "#str")
	public PageBean findByDate(String begintime, String endtime, int page, int size,String str) {
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
	@Cacheable(value = "findDateNum",key = "#str")
	public int findDateNum(String begintime, String endtime) {
		int i = dao.findDateNum(begintime, endtime);
		return i;
	}

	@Override
	@Cacheable(value = "findById",key = "#id")
	public ComplaintBean findById(int id) {
		ComplaintBean bean = dao.findById(id);
		return bean;
	}

	@Override
	@CacheEvict(value="*",allEntries = true)
	public void updateOrder(int id) {
		int accusesum = dao.findCreNum(id);
		int ordersum = dao1.selectOrderNumberByMem2(id);
		int ordersum1 = dao1.selectOrderNumberByMem1(id);
		ordersum=ordersum+ordersum1;
		double accu = accusesum;
		double order = ordersum;
		double credibility = 1-(accu/order);
		dao2.updateCreOrder(credibility, id);
	}
	

}
