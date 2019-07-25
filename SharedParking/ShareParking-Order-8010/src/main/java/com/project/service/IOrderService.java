package com.project.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.Bean.OrderBean;
import com.project.Bean.PageBean;




/**
 * 订单业务
 * @author x1c
 *
 */
public interface IOrderService {

	//添加订单信息
	public int insertOrder(OrderBean orderBean);
	
	//通过订单号查询订单
	public OrderBean selectOrderByOrderNumber(String orderNumber);
	//通过订单id查询订单
	public OrderBean selectOrderById(int oid);
			
	//通过状态分页查询订单（租客）
	public PageBean selectOrderByState1(int mid, int status, int page, int size);
	//通过状态分页查询订单（出租者）
	public PageBean selectOrderByState2(int mid, int status, int page, int size);
	
	//统计订单
	public List<Double> selectOrderByMonth(int year, int startMonth, int endMonth);
	//统计订单（日期）
	public List<Double> selcetOrderByDate(String date);
	
	//修改订单状态
	public int updateOrderAttr(OrderBean orderBean);
	//查询每日新增订单数
	public int selectOrderNumberByDate(String date);
	//每日订单
	public 	PageBean selcetOrderByDay(String date, int page, int size);

	//通过状态分页查询订单（后台）
	public PageBean selectOrderByStatus(int status, int page, int size);

	


}
