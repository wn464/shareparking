package com.project.Bean;

import java.util.List;

import lombok.Data;


@Data
public class OrderBean {

	private int id;//订单主键
	private String orderTime;//订单生成时间
	private String orderNumber;//订单号
	private String alipayNumber;//支付宝号
	private double price;//订单金额
	private int delState;//删除状态
	private String carNumber;
	private String beginTime;
	private String endTime;
	
}
