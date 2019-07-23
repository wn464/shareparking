package com.project.controller;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Bean.CarportBean;
import com.project.Bean.PageBean;
import com.project.IService.CarportIService;

@Controller
public class CarportController {
	@Autowired
private CarportIService service;
	/**
	 * 通过所属人查询车位
	 * @param mid
	 * @return
	 */
	@GetMapping(value="/carport/mid/{mid}")
	@ResponseBody
public CarportBean findcarportbymid(@PathVariable("mid")int mid) {
	CarportBean carport =service.findcarportbymid(mid);
	return carport;
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
		List<CarportBean> page=service.findcarportbycoordinate(coordinate_x, coordinate_y);
		return page;
	}
	/**
	 * 查询可租车位
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/carport/statusy/{page}/{size}")
	@ResponseBody
	public PageBean findcarporty(@PathVariable("page")int page,@PathVariable("size")int size) {
		PageBean pagebean=service.findcarportbykezu(page, size);
		return pagebean;
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
	@PutMapping(value="/carport/{cid}")
	public boolean updatestatusy(@PathVariable("cid")int cid) {
		boolean l=service.updatecarportstatusy(cid);
		return l;
	}
}
