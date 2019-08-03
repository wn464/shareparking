package com.project.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.project.controller.interfaces.CredibilityHandler;
import com.project.controller.interfaces.LogRemoter;
import com.project.controller.interfaces.MemberRemoter;
import com.project.controller.interfaces.carportHandler;
import com.project.service.IOrderService;
import com.project.util.CountPrice;
@RestController
public class OrderHandler {

	@Autowired
	private IOrderService orderService;
	@Autowired
	private CredibilityHandler credibilityHandler;
	@Autowired
	private MemberRemoter memberremoter;
	@Autowired
	private LogRemoter logRemoter;
	@Autowired
	private carportHandler carportHandler ;
	
	/*
	 * 添加订单
	 */
	@PostMapping("/order")
	public Integer insertOrder(HttpSession session,OrderBean orderBean) {
		System.out.println("添加订单-----"+orderBean);
		int mid =  (int) session.getAttribute("memberid");
		System.out.println("member"+memberremoter.findId(mid));
        orderBean.setMemberBean1(memberremoter.findId(mid));
        orderBean.setCarNumber(memberremoter.findId(mid).getList().get(0).getCarnumber());
		int num = orderService.insertOrder(orderBean);
		carportHandler.updatestatusf(orderBean.getCarportBean().getId());
		String membername =  (String) session.getAttribute("membername");
		logRemoter.insertLog(membername, "添加订单");//测试数据
	
		return num;
	}
	/*
	 * 生成订单
	 */
	@GetMapping("/order/{oid}")
	public OrderBean selectOrderById(HttpServletRequest request,@PathVariable("oid")int oid) {
		OrderBean orderBean = orderService.selectOrderById(oid);
		return orderBean;
	}
	/*
	 * 租客分页查询
	 */
	@GetMapping("/order/status1/{status}/{page}/{size}")
	public PageBean selectOrderByState1(@PathVariable("status")int status, @PathVariable("page")int page, @PathVariable("size")int size,HttpSession session) {
		int mid = (int) session.getAttribute("memberid");
		System.out.println("-------"+mid);
//		int mid = 1;//测试数据
		 String str = mid+""+page+""+status;
		PageBean pageBean = orderService.selectOrderByState1(mid, status, page, size,str);
		System.out.println("pagebena"+pageBean);
		return pageBean;
	}
	/*
	 * 出租客分页查询
	 */
	@GetMapping("/order/status2/{status}/{page}/{size}")
	public PageBean selectOrderByState2(HttpSession session,@PathVariable("status")int status, @PathVariable("page")int page, @PathVariable("size")int size) {
		int mid = (int) session.getAttribute("memberid");
		System.out.println("-------"+mid);
//		int mid = 2;//测试数据
		 String str = mid+""+page+""+status;
		PageBean pageBean = orderService.selectOrderByState2(mid, status, page, size,str);
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
		System.out.println("handler"+year);
		OrderDTO orderDTO = new OrderDTO();
		double m[] = new double[12];
		List<Double> list = new ArrayList<Double>();
		String str = year+""+smonth+""+emonth+"";
		System.out.println("str---------"+str);
		list = orderService.selectOrderByMonth(year, smonth, emonth,str);
		for (Double double1 : list) {
			System.out.println(double1);
		}
		
		int a = 0;
		for (int i = smonth-1; i < emonth; i++) {
			System.out.println(list.get(a));
			m[i] = list.get(a);
			a++;
		}	
		orderDTO.setDouble1(m);
		
		int m1[] = new int[12];
		List<Integer> list1 = new ArrayList<Integer>();
		list1 = orderService.selectOrderCount(year, smonth, emonth,str);
		System.out.println(list);
		int a1 = 0;
		for (int i = smonth-1; i < emonth; i++) {
			m1[i] = list1.get(a1);
			a1++;
		}	
		orderDTO.setTimes(m1);
		
		return orderDTO;
	}
	
	//按时间段统计订单
	@GetMapping("/order/month/{year}/{smonth}/{emonth}")
	public double[] selcetOrderByMonth(@PathVariable("year")int year,@PathVariable("smonth")int smonth,@PathVariable("emonth")int emonth) {
		System.out.println(year);
		double m[] = new double[12];
		List<Double> list = new ArrayList<Double>();
		String str = year+""+smonth+""+emonth+"";
		list = orderService.selectOrderByMonth(year, smonth, emonth,str);
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
		String str = year+""+smonth+""+emonth+"";
		list = orderService.selectOrderCount(year, smonth, emonth,str);
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
		String str = year+""+month;
		List<Double> list = orderService.selectOrderByMonth(year, month, month,str);
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
		String str = date+"";
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
		String str = year+"";
		List<Double> list = orderService.selectOrderByMonth(year, 1, 12,str);
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
		String str = date+""+page+"";
		PageBean pageBean = orderService.selcetOrderByDay(date, page, size,str);
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
		String str = status+""+page+"";
		PageBean pageBean = orderService.selectOrderByStatus(status, page, size,str);
		return pageBean;
	}
	/*
	 * 修改价格生成订单
	 */
	@GetMapping("/order/pay/{oid}/{price}")
	public OrderBean payMoney(@PathVariable("oid")int oid,@PathVariable("price")double price,HttpSession session) {
		System.out.println("付款订单"+price);
		OrderBean orderBean = orderService.selectOrderById(oid);
		System.out.println(orderBean);
		double totalPrice = CountPrice.countPrice(orderBean.getOrderTime(),price);
		System.out.println(totalPrice);
		//修改订单价格
		OrderBean orderBean2 = new OrderBean();
		orderBean2.setPrice(Math.floor(totalPrice));
		orderBean2.setId(oid);
		System.out.println("修改价格"+orderBean2);
		orderService.updateOrderAttr(orderBean2);
		OrderBean order = orderService.selectOrderById(oid);
		return order;
	}
	
	//确认订单，修改信誉表
	@GetMapping("/order/com")
	public int comfirm(HttpSession session) {
		System.out.println("com");
		int id =  (int) session.getAttribute("memberid");
		int num1 = credibilityHandler.updateCreOrder(id);
		System.out.println("---------------"+num1);
		return num1;
	}
	
		
	
}
