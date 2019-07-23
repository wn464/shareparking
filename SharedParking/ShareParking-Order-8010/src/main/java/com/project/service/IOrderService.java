package com.project.service;


import com.project.Bean.OrderBean;



/**
 * 订单业务
 * @author x1c
 *
 */
public interface IOrderService {

	//添加并获取订单信息
	public OrderBean getOrder(OrderBean orderBean);
	
	
}
