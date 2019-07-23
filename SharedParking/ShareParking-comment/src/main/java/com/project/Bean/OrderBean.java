package com.project.Bean;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class OrderBean implements Serializable{

	private int id;//订单主键
	private String orderTime;//订单生成时间
	private String orderNumber;//订单号
	private String alipayNumber;//支付宝号
	private double price;//订单金额
	private String carNumber;//车牌号
	private String beginTime;//租车开始时间
	private String endTime;//租车结束时间
	private CarportBean carportBean;//车位
	private MemberBean memberBean1;//租客
	private MemberBean memberBean2;//出租方
	private MarkBean statusBean;//状态
	private MarkBean delState;//删除状态
}
