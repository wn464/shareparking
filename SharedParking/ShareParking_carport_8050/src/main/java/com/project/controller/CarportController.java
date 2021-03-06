package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.Bean.CarportBean;
import com.project.Bean.MarkBean;
import com.project.Bean.MemberBean;
import com.project.Bean.PageBean;
import com.project.IService.CarportIService;
import com.project.controller.interfaces.LogRemoter;
import com.project.controller.interfaces.MemberRemoter;

@Controller
public class CarportController {
	@Autowired
private CarportIService service;
@Autowired	
private LogRemoter logservice;
	/**
	 * 通过所属人查询车位
	 * @param mid
	 * @return
	 */
	@GetMapping(value="/carport/mid")
	@ResponseBody
public List<CarportBean> findcarportbymid(String mid,String address) {
		String key=mid+address;
		
		List<CarportBean> carports =service.findcarportbymid(mid,address,key);
	return carports;
			}
	/**
	 * 通过车位id查询车位
	 * @param cid
	 * @return
	 */
	@GetMapping(value="/carport/cid/{cid}")
	@ResponseBody
	public CarportBean findcarportbycid(@PathVariable("cid")int cid) {
		CarportBean carport=service.findcarportbycid(cid);
		return carport;
	}
	/**
	 * 通过车位编号查询车位
	 * @param number
	 * @return
	 */
	@GetMapping(value="/carport/number/{number}")
	@ResponseBody
	public CarportBean findcarportbycarportnumber(@PathVariable("number")String number) {
		CarportBean carport=service.findcarportbynumber(number);
		return carport;
	}
	/***
	 * 通过坐标查询附近车位
	 * @param coordinate_x
	 * @param coordinate_y
	 * @return
	 */
	@GetMapping(value="/carport/coordinate/{x}/{y}")
	@ResponseBody
	public List<CarportBean> findcarportbycoordinate(@PathVariable("x")double coordinate_x,@PathVariable("y")double coordinate_y) {
		System.out.println(coordinate_x);
		String key=""+coordinate_x+coordinate_y+"";
		List<CarportBean> page=service.findcarportbycoordinate(coordinate_x, coordinate_y,key);
		return page;
	}
	/**
	 * 查询可租车位
	 * @param page
	 * @param size
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping(value="/carport/statusy/{page}/{size}")
	@ResponseBody
	public PageBean findcarporty(@PathVariable("page")int page,@PathVariable("size")int size) {
		PageBean pagebean=service.findcarportbykezu(page, size);
		return pagebean;
	}
	public PageBean fallback(@PathVariable("page")int page,@PathVariable("size")int size) {
		PageBean pa=new PageBean().setMessage("兄弟你的网络开小差了");
		return pa;
	}
	/****
	 * 查询不可租车位
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/carport/statusf/{page}/{size}")
	@ResponseBody
	public PageBean findcarportbukezu(@PathVariable("page")int page,@PathVariable("size")int size) {
		PageBean pagebean=service.findcarportbyunkezu(page, size);
		return pagebean;
	}
	/**
	 * 修改车位为可租状态
	 * @param cid
	 * @return
	 */
	@PutMapping(value="/carport/yes/{cid}")
	@ResponseBody
	public boolean updatestatusy(@PathVariable("cid")int cid) {
		boolean l=service.updatecarportstatusy(cid);
		return l;
	}
	/**
	 * 修改车位为不可租状态
	 * @param cid
	 * @return
	 */
	@PutMapping(value="/carport/false/{cid}")
	@ResponseBody
	public boolean updatestatusf(@PathVariable("cid")int cid) {
		boolean l=service.updatecarportstatusf(cid);
		return l;
	}
	/**
	 * 修改车位的审核状态
	 * @param cid
	 * @return
	 */
	@PutMapping(value="/carport/audit/{cid}/{audit}")
	@ResponseBody
	public boolean updateauditstatus(@PathVariable("cid")int cid,@PathVariable("audit")int audit) {
		boolean l=service.updatecarauditstatus(cid,audit);
		return l;
	} 
	/**
	 * 添加车位
	 * @param carport
	 * @return
	 */
	@PostMapping("/carport")
	@ResponseBody
	public boolean addcarport(CarportBean carport,HttpSession session) {
		System.out.println("++++++++"+carport);
		int id=(int) session.getAttribute("memberid");
        MemberBean m=new MemberBean();
        m.setId(id);
        carport.setM_id(m);
		SimpleDateFormat datafromat=new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
		Date te =new Date();
		String a=datafromat.format(te);
		carport.setAddtime(a);
		String number="ABCD"+carport.hashCode();
		carport.setCarportnumber(number);
		boolean l =service.addcarport(carport);
		String name=(String) session.getAttribute("membername");
		String message=name+"新增了一个车位";
		 logservice.insertLog(name, message); 
		return l;
	}
	/**
	 * 查询今日新增车位
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/carport/time/{page}/{size}")
	@ResponseBody
	public PageBean findcarportbyday(@PathVariable("page")int page,@PathVariable("size")int size) {
		PageBean pagebean=service.findcarportbyday(page, size);
		
		return pagebean;
	}
	/**
	 * 查询未审核车辆
	 * @return
	 */
	@GetMapping(value="/carport/auauditstatus")
	@ResponseBody
	public List<CarportBean> findcarportbyAuauditstatus(){
		List<CarportBean> carports=service.findcarportbyauditstatus();
		return carports;
	}
	/**
	 * 用户再次出租车位
	 * @param id
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	@PostMapping(value="/carport/updatastatus/cz")
	@ResponseBody
public boolean updatacarport(CarportBean carport) {
		System.out.println("+++++++++"+carport);
	MarkBean status=new MarkBean();
	status.setId(10);
	carport.setStatus(status);
	boolean l=service.updatacarport(carport);
	return l;
}
	/**
	 * 通过车位所属人id查询该用户所有车位
	 * @param memid
	 * @return
	 */
	@GetMapping(value="/carport/memid")
	@ResponseBody
	public List<CarportBean> findcarportbymemid(HttpSession session){
		int memid=(int) session.getAttribute("memberid");
		
		List<CarportBean> ports=service.findcarportbymemid(memid);
		
		
		return ports;
	}
}
