package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.Bean.OrderBean;

public interface IOrderDao {

	//添加订单
	public int insertOrder(OrderBean orderBean);
	
	//通过订单号查询订单id
	public OrderBean selectOrderByOrderNumber(String orderNumber);
	//通订单id查询订单
	public OrderBean selectOrderById(int oid);
	
	//通过状态分页查询订单（租客）
	public List<OrderBean> selectOrderByState1(@Param("mid")int mid,@Param("status")int status,@Param("page")int page,@Param("size")int size);
	//通过状态分页查询订单（出租者）
	public List<OrderBean> selectOrderByState2(@Param("mid")int mid,@Param("status")int status,@Param("page")int page,@Param("size")int size);
	//根据状态查询订单总数量（租客）
	public int selectNumberByState1(@Param("mid")int mid,@Param("status")int status);
	//根据状态查询订单总数量（出租者）
	public int selectNumberByState2(@Param("mid")int mid,@Param("status")int status);
	
	//统计订单
	public List<Double> selectOrderByMonth(@Param("startTime")String startTime,@Param("endTime")String endTime);
	//统计订单（日期）
	public List<Double> selcetOrderByDate(@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	//修改订单属性
	public int updateOrderAttr(OrderBean orderBean);
	
	//每日新增订单数
	public int selcetOrderNumberByDate(@Param("startTime")String startTime,@Param("endTime")String endTime);

	//每日订单
	public List<OrderBean> selcetOrderByDay(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("page")int page,@Param("size")int size);


}
