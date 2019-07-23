package com.project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Bean.MemberBean;
import com.project.Bean.OrderBean;
import com.project.Bean.PageBean;
import com.project.service.IOrderService;

@RestController
public class OrderHandler {

	@Autowired
	private IOrderService orderService;
	
	/*
	 * 添加订单
	 */
	@PostMapping("/order")
	public String insertOrder(OrderBean orderBean) {
        MemberBean memberBean1 = new MemberBean();
        memberBean1.setId(1);
        orderBean.setMemberBean1(memberBean1);
		int num = orderService.insertOrder(orderBean);
		return "ok";
	}
	/*
	 * 生成订单
	 */
	@GetMapping("/order/{oid}")
	public OrderBean selectOrderById(@PathVariable("oid")int oid) {
		OrderBean orderBean = orderService.selectOrderById(oid);
		return orderBean;
	}
	/*
	 * 租客分页查询
	 */
	@GetMapping("/order/status/{status}/{page}/{size}")
	public PageBean selectOrderByState1(@PathVariable("status")int status, @PathVariable("page")int page, @PathVariable("size")int size) {
		 int mid = 1;//测试数据
		PageBean pageBean = orderService.selectOrderByState1(mid, status, page, size);
		return pageBean;
	}
	/*
	 * 出租客分页查询
	 */
	@GetMapping("/order/status/{status}/{page}/{size}")
	public PageBean selectOrderByState2(@PathVariable("status")int status, @PathVariable("page")int page, @PathVariable("size")int size) {
		 int mid = 2;//测试数据
		PageBean pageBean = orderService.selectOrderByState2(mid, status, page, size);
		return pageBean;
	}
	
	/*
	 * 修改订单属性
	 */
	@PutMapping("/order/attr")
	public int updateOrderAttr(OrderBean orderBean) {
		int num = orderService.updateOrderAttr(orderBean);
		return num;
	}
	
	/*
	 * 按月份查询收入
	 */
	@GetMapping("/mth")
	public double selcetOrderByDate(Integer year,Integer month,ModelMap map) {
		List<Double> list = orderService.selectOrderByMonth(year, month, month);
		double mprice = 0;
		for (Double double1 : list) {
			mprice+=double1;
		}
		return mprice;
	}
	/*
	 * 按日期查询收入
	 */
	@GetMapping("/dates")
	public double selcetOrderByDate(String date) {
		System.out.println(date);
		List<Double> list = orderService.selcetOrderByDate(date);
		double dprice = 0;
		for (Double double1 : list) {
			dprice+=double1;
		}
		return dprice;
	}
	/*
	 * 按年份查询收入
	 */
	@GetMapping("/year")
	@ResponseBody
	public double selcetOrderByYear(int year,ModelMap map) {
		List<Double> list = orderService.selectOrderByMonth(year, 1, 12);
		System.out.println(list);
		double yprice = 0;
		for (Double double1 : list) {
			yprice+=double1;
		}
		return yprice;
	}
	/*
	 * 统计每日新增订单数
	 */
	@GetMapping("/order/dayNumber/{date}")
	public int selectOrderNumberByDate(@PathVariable("date")String date) {
		int num = orderService.selectOrderNumberByDate(date);
		return num;
	}
	/*
	 * 分页查询每日订单
	 */
	@GetMapping("/order/dateOrder/{date}/{page}/{size}")
	public PageBean selcetOrderByDay(@PathVariable("date")String date,@PathVariable("page")int page,@PathVariable("size")int size){
		PageBean pageBean = orderService.selcetOrderByDay(date, page, size);
		return pageBean;
		
	}
		
}
