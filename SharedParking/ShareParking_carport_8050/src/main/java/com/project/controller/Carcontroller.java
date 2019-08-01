package com.project.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.Bean.CarBean;
import com.project.Bean.MemberBean;
import com.project.IService.carService;

@Controller
public class Carcontroller {
	@Autowired
private carService service;
	@PostMapping(value="/car/add")
	@ResponseBody
	public boolean addcar(CarBean car,HttpSession session) {
		int mid=(int) session.getAttribute("memberid");
		MemberBean m=new MemberBean();
		m.setId(mid);
		car.setM_id(m);
		boolean s=service.addcar(car);
		return s;
	}
	@GetMapping(value="/car/find")
	@ResponseBody
	public List<CarBean> findcars(HttpSession session){
	int mid=	(int) session.getAttribute("memberid");
		List<CarBean> cars=service.findcars(mid);
		return cars;
		
	}
}
