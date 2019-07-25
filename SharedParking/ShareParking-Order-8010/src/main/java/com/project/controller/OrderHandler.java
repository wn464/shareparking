package com.project.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.Bean.MemberBean;
import com.project.Bean.OrderBean;
import com.project.Bean.OrderDTO;
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
		System.out.println("-----"+orderBean);
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
		System.out.println("111111111-=-=-=-=-=-=-"+oid);
		OrderBean orderBean = orderService.selectOrderById(oid);
		System.out.println("-----========="+orderBean);
		return orderBean;
	}
	/*
	 * 租客分页查询
	 */
	@GetMapping("/order/status1/{status}/{page}/{size}")
	public PageBean selectOrderByState1(@PathVariable("status")int status, @PathVariable("page")int page, @PathVariable("size")int size) {
		 int mid = 1;//测试数据
		PageBean pageBean = orderService.selectOrderByState1(mid, status, page, size);
		return pageBean;
	}
	/*
	 * 出租客分页查询
	 */
	@GetMapping("/order/status2/{status}/{page}/{size}")
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
	
	//按时间段统计订单
	@GetMapping("/order/money/{year}/{smonth}/{emonth}")
	public OrderDTO countMoney(@PathVariable("year")int year,@PathVariable("smonth")int smonth,@PathVariable("emonth")int emonth) {
		OrderDTO orderDTO = new OrderDTO();
		double m[] = new double[12];
		List<Double> list = new ArrayList<Double>();
		list = orderService.selectOrderByMonth(year, smonth, emonth);
		System.out.println(list);
		int a = 0;
		for (int i = smonth-1; i < emonth; i++) {
			m[i] = list.get(a);
			a++;
			System.out.println("---------"+m[i]);
		}	
		orderDTO.setDouble1(m);
		
		int m1[] = new int[12];
		List<Integer> list1 = new ArrayList<Integer>();
		list1 = orderService.selectOrderCount(year, smonth, emonth);
		System.out.println(list);
		int a1 = 0;
		for (int i = smonth-1; i < emonth; i++) {
			m1[i] = list1.get(a1);
			a1++;
			System.out.println("---------"+m1[i]);
		}	
		orderDTO.setTimes(m1);
		
		return orderDTO;
	}
	
	//按时间段统计订单
	@GetMapping("/order/month/{year}/{smonth}/{emonth}")
	public double[] selcetOrderByMonth(@PathVariable("year")int year,@PathVariable("smonth")int smonth,@PathVariable("emonth")int emonth) {
		double m[] = new double[12];
		List<Double> list = new ArrayList<Double>();
		list = orderService.selectOrderByMonth(year, smonth, emonth);
		System.out.println(list);
		int a = 0;
		for (int i = smonth-1; i < emonth; i++) {
			m[i] = list.get(a);
			a++;
			System.out.println("---------"+m[i]);
		}	
		
		return m;
	}

	//按时间段统计订单次数
	@GetMapping("/order/count/{year}/{smonth}/{emonth}")
	public Integer[] selcetOrderCount(@PathVariable("year")int year,@PathVariable("smonth")int smonth,@PathVariable("emonth")int emonth) {
		Integer m[] = new Integer[12];
		List<Integer> list = new ArrayList<Integer>();
		list = orderService.selectOrderCount(year, smonth, emonth);
		System.out.println(list);
		int a = 0;
		for (int i = smonth-1; i < emonth; i++) {
			m[i] = list.get(a);
			a++;
			System.out.println("---------"+m[i]);
		}	
		return m;
	}
	/*
	 * 按月份查询收入
	 */
	@GetMapping("/order/mth/{year}/{month}")
	public double selcetOrderByDate(@PathVariable("year")Integer year,@PathVariable("month")Integer month) {
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
	@GetMapping("/order/dates/{date}")
	public double selcetOrderByDate(@PathVariable("date")String date) {
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
	@GetMapping("/order/year/{year}")
	@ResponseBody
	public double selcetOrderByYear(@PathVariable("year")int year) {
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
	
	/*
	 * 通过订单号查询订单
	 */
	@GetMapping("/order/numOrder/{num}")
	public PageBean selsctOrderBynum(@PathVariable("num")String num){
		
		OrderBean bean = orderService.selectOrderByOrderNumber(num);
		
		List<OrderBean> list = new ArrayList<OrderBean>();
		list.add(bean);
		PageBean page =new PageBean();
		page.setList(list);
		
		return page;
		
	}
	
	/*
	 * 后台根据订单状态分页查询订单
	 */
	@GetMapping("/order/status/{status}/{page}/{size}")
	public PageBean selectOrderByStatus(@PathVariable("status")int status, @PathVariable("page")int page, @PathVariable("size")int size) {
		PageBean pageBean = orderService.selectOrderByStatus(status, page, size);
		return pageBean;
	}
		
}
