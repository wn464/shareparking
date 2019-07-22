package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.Bean.CarportBean;
import com.project.IService.CarportIService;

@Controller
public class CarportController {
	@Autowired
private CarportIService service;
	@GetMapping(value="/carport")
	@ResponseBody
public CarportBean findcarportbymid(int mid) {
		System.out.println("000000");
	CarportBean carport =service.findcarportbymid(mid);
	return carport;
			}
}
