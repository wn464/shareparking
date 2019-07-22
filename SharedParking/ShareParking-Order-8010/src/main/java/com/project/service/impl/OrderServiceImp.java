package com.project.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Bean.OrderBean;
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
	@Override
	public OrderBean getOrder(OrderBean orderBean) {
	//生成订单时间
	String orderTime= CreateOrderInfo.getCreateTime();
  	orderBean.setOrderTime(orderTime);
  	//生成订单号
    String orderNumber =  CreateOrderInfo.getOrderNumber();
    orderBean.setOrderNumber(orderNumber);
    //添加订单
    int num =orderDao.insertOrder(orderBean);

		return null;
	}

}
