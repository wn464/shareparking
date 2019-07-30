package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.project.Bean.MarkBean;
import com.project.Bean.OrderBean;
import com.project.service.IOrderService;
import com.project.util.AlipayUtil;
import com.project.util.CountPrice;


@Controller
public class PayController {
	@Autowired
	private IOrderService orderService;
	
	//调用支付宝接口
	@GetMapping("/pay/{oid}")
	@ResponseBody
	public String pay(@PathVariable("oid")int oid) {
		
		OrderBean orderBean = orderService.selectOrderById(oid);
		String string = null;
		try {
			double price = orderBean.getPrice();
			string = AlipayUtil.pay(orderBean.getOrderNumber(),price,"支付");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return string;
	}
	
	//退款
	@GetMapping("/refund/{oid}")
	public String refund(@PathVariable("oid")int oid,ModelMap map) {
		OrderBean orderBean = orderService.selectOrderById(oid);
		System.out.println(orderBean.getPrice());
		boolean boo=false;
		String s="";
		try {
			
			System.out.println("--------------"+orderBean);
		 boo=AlipayUtil.refund(orderBean.getOrderNumber(), orderBean.getPrice());
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(boo);
		if(boo) {
//			s="退款成功,款项已发回你支付宝账户！！！！！";
//			OrderBean orderBean2 = new OrderBean();
//			orderBean2.setId(oid);
//			MarkBean status=new MarkBean();
//			status.setId(13);
//			orderBean2.setSubscribeStatus(status);
//			orderService.updateOrderAttr(orderBean2);
		}else {
		s="退款失败，详情请联系卖家！！！！！";
		}
		map.addAttribute("tk", s);
		return "tuikuan.html";
	}
	//响应回调
	@RequestMapping("/ret")
	public  String ret(HttpServletRequest request) {
		System.out.println("------响应回调---------------");
		String[] num = null;
		try {
			num = AlipayUtil.returnUrl(request);
			System.out.println(num[1]);
			OrderBean orderBean = orderService.selectOrderByOrderNumber(num[0]);
//			//修改支付宝号
			OrderBean orderBean2 = new OrderBean();
			orderBean2.setId(orderBean.getId());
			orderBean2.setAlipayNumber(num[1]);
			orderService.updateOrderAttr(orderBean2);
			//修改订单状态
			OrderBean orderBean3 = new OrderBean();
			orderBean3.setId(orderBean.getId());
			MarkBean statusBean = new MarkBean();
			statusBean.setId(4);
			orderBean3.setStatusBean(statusBean);
			orderService.updateOrderAttr(orderBean3);
			
			//发送推送消息
//			System.out.println("推送-------------------"+orderBean.getId());
//			String id = String.valueOf(orderBean.getId());
//			WebSocketUtil.sendMessageAll(id);
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("回调");
		return "redirect:/index.html";
	}
	
	//响应回调1
	@RequestMapping("/ret1")
	@ResponseBody
	public  String ret1(HttpServletRequest request) {
		System.out.println("------响应回调1---------------");
		return null;
	}
}
