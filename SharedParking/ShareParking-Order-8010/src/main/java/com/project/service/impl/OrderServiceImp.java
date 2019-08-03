package com.project.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.Bean.MarkBean;
import com.project.Bean.OrderBean;
import com.project.Bean.PageBean;
import com.project.dao.IOrderDao;
import com.project.service.IOrderService;
import com.project.util.CreateOrderInfo;
/**
 * 订单业务
 * @author x1c
 *
 */
@Service
public class OrderServiceImp implements IOrderService{
	@Autowired
	private IOrderDao orderDao;
	
	
	//添加订单
	/**
	 *
	 */
	@Override
	@CacheEvict(value= {"selectOrderById","selectOrderByOrderNumber","selectOrderByState1","selectOrderByState2","selectOrderByMonth","selectOrderCount","selcetOrderByDate","selectOrderNumberByDate","selcetOrderByDay","selectOrderByStatus"},allEntries = true)
	public int insertOrder(OrderBean orderBean) {
	//生成订单时间
	String orderTime= CreateOrderInfo.getCreateTime();
  	orderBean.setOrderTime(orderTime);
  	//生成订单号
    String orderNumber =  CreateOrderInfo.getOrderNumber();
    orderBean.setOrderNumber(orderNumber);
    MarkBean status = new MarkBean();
    status.setId(3);
    orderBean.setStatusBean(status);
    
	System.out.println("order++++++++==========================-"+orderBean);
    //添加订单
   orderDao.insertOrder(orderBean);
   int num=orderBean.getId();
		return num;
	}
	
	//通过订单号查询
	@Override
	@Cacheable(value = "selectOrderByOrderNumber",key = "#orderNumber")
	public OrderBean selectOrderByOrderNumber(String orderNumber) {
		OrderBean orderBean = orderDao.selectOrderByOrderNumber(orderNumber);
		return orderBean;
	}
	
	//通过订单id查询
	@Override
	@Cacheable(value = "selectOrderById",key = "#oid")
	public OrderBean selectOrderById(int oid) {
		OrderBean orderBean = orderDao.selectOrderById(oid);
		return orderBean;
	}
	
	//租客分页查询
	@Override
	@Cacheable(value = "selectOrderByState1",key = "#str")
	public PageBean selectOrderByState1(int mid, int status, int page, int size,String str) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		page = (page-1)*size;
		List<OrderBean> list = orderDao.selectOrderByState1(mid, status, page, size);
		int totalNumber  = orderDao.selectNumberByState1(mid, status);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}
	
	//出租客分页查询
	@Override
	@Cacheable(value = "selectOrderByState2",key = "#str")
	public PageBean selectOrderByState2(int mid, int status, int page, int size,String str) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		page = (page-1)*size;
		List<OrderBean> list = orderDao.selectOrderByState2(mid, status, page, size);
		int totalNumber  = orderDao.selectNumberByState2(mid, status);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}
	
	//修改订单属性
	@Override
	@CacheEvict(value={"selectOrderById","selectOrderByOrderNumber","selectOrderByState1","selectOrderByState2","selectOrderByMonth","selectOrderCount","selcetOrderByDate","selectOrderNumberByDate","selcetOrderByDay","selectOrderByStatus"},allEntries = true)
	public int updateOrderAttr(OrderBean orderBean) {
		System.out.println(orderBean+"修改状态");
		int num = orderDao.updateOrderAttr(orderBean);
		return num;
	}

	//按月份统计一段时间的订单-------------------------------------
	@Override
	@Cacheable(value = "selectOrderByMonth",key = "#str")
	public List<Double> selectOrderByMonth(int year, int startMonth, int endMonth,String str) {
		System.out.println("---------"+year);
		//存放每月总金额
		List<Double> list = new ArrayList<Double>();
		for (int i = startMonth; i <= endMonth; i++) {
			//1-10月
			if (i<10) {
				double mprice = 0;
				String startTime = year+"-"+"0"+i+"-01"+" "+"00:00:00";
				String endTime = year+"-"+"0"+i+"-31"+" "+"00:00:00";
				System.out.println("st"+startTime);
				System.out.println("ed"+endTime);
				List<Double> list1 = orderDao.selectOrderByMonth(startTime, endTime);
				for (Double double1 : list1) {
					mprice+=double1;
				}
				list.add(mprice);
			}
			//11-12月
			else {
				double mprice = 0;
				String startTime = year+"-"+i+"-01"+" "+"00:00:00";
				String endTime = year+"-"+i+"-31"+" "+"00:00:00";
				System.out.println(endTime);
				List<Double> list2 = orderDao.selectOrderByMonth(startTime, endTime);
				for (Double double1 : list2) {
					mprice+=double1;
				}
				list.add(mprice);
			}
			
		}
		System.out.println("-----------"+list);
		return list;

	}
	@Override
	@Cacheable(value = "selectOrderCount",key = "#str")
	public List<Integer> selectOrderCount(int year, int startMonth, int endMonth,String str) {
		//存放每月总金额
		List<Integer> list = new ArrayList<Integer>();
		for (int i = startMonth; i <= endMonth; i++) {
			//1-10月
			if (i<10) {
				Integer count = 0;
				String startTime = year+"-"+"0"+i+"-01"+" "+"00:00:00";
				String endTime = year+"-"+"0"+i+"-31"+" "+"00:00:00";
				List<Integer> list1 = orderDao.selectOrderCount(startTime, endTime);
				System.out.println(list1);
				for (Integer in : list1) {
					count+=in;
				}
				list.add(count);
			}
			//11-12月
			else {
				Integer count = 0;
				String startTime = year+"-"+i+"-01"+" "+"00:00:00";
				String endTime = year+"-"+i+"-31"+" "+"00:00:00";
				System.out.println(endTime);
				List<Integer> list1 = orderDao.selectOrderCount(startTime, endTime);
				System.out.println(list1);
				for (Integer in : list1) {
					count+=in;
				}
				list.add(count);
			}
			
		}
		System.out.println("-----------"+list);
		return list;

	}
	
	
	//统计某日订单金额
	@Override
	@Cacheable(value = "selcetOrderByDate",key = "#date")
	public List<Double> selcetOrderByDate(String date) {
		String startTime = date+" 00:00:00";
		String endTime = date+" 23:59:59";
		List<Double> price = orderDao.selcetOrderByDate(startTime, endTime);
		return price;
	}
	
	//统计每日新增订单数
	@Override
	@Cacheable(value = "selectOrderNumberByDate",key = "#date")
	public int selectOrderNumberByDate(String date) {
		String startTime = date+" 00:00:00";
		String endTime = date+" 23:59:59";
		int num = orderDao.selcetOrderNumberByDate(startTime, endTime);
		return num;
	}
	//分页查询每日订单
	@Override
	@Cacheable(value = "selcetOrderByDay",key = "#str")
	public PageBean selcetOrderByDay(String date,int page,int size,String str){
		String startTime = date+" 00:00:00";
		String endTime = date+" 23:59:59";
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		page = (page-1)*size;
		List<OrderBean> list = orderDao.selcetOrderByDay(startTime, endTime, page, size);
		int totalNumber = orderDao.selcetOrderNumberByDate(startTime, endTime);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
		
	}

	//通过状态分页查询订单（后台）
	@Override
	@Cacheable(value = "selectOrderByStatus",key = "#str")
	public PageBean selectOrderByStatus(int status, int page, int size,String str) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(page);
		page = (page-1)*size;
		List<OrderBean> list = orderDao.selectOrderByStatus(status, page, size);
		int totalNumber  = orderDao.selectNumberByStatus(status);
		pageBean.setSize(size);
		pageBean.setTotalNumber(totalNumber);
		int totalPage = (totalNumber%size==0)?totalNumber/size:(totalNumber/size)+1;
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		return pageBean;
	}
	

	
	
}
