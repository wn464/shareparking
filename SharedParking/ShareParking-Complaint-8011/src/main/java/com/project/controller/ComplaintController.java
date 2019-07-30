package com.project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Bean.ComplaintBean;
import com.project.Bean.PageBean;
import com.project.service.IComplaintService;

@RestController
public class ComplaintController {
	@Autowired
	private IComplaintService service;
	
	@PostMapping("/complaint")
	public int addComplaint(@RequestBody ComplaintBean bean) {
		System.out.println("名字："+bean);
		service.addComplaint(bean);
		return 1;
	}
	
	@GetMapping("/complaint/user/{id}")
	public List<ComplaintBean> findBymmid(@PathVariable("id")int id){
		List<ComplaintBean> list=service.findBymid(id);
		return list;
	}
	
	@GetMapping("/complaint/order/{oid}/{mid}")
	public ComplaintBean findByOrder(@PathVariable("oid")int oid,@PathVariable("mid")int mid) {
		String str = oid+""+mid;
		ComplaintBean bean = service.findByoid(oid,mid,str);
		System.out.println("对象"+bean);
		return bean;
	}
	
	@GetMapping("/complaint/{status}/{page}/{size}")
    public PageBean findComplaint(@PathVariable("status")int status,@PathVariable("page")int page,@PathVariable("size")int size){
		String str = status+""+page;
		PageBean bean =service.findByStatus(status, page, size,str);
		return bean;
	}
	
	@GetMapping("/complaint/{begintime}/{endtime}/{page}/{size}")
    public PageBean findComplaintbydate(@PathVariable("begintime")String begintime, @PathVariable("endtime")String endtime, @PathVariable("page")int page, @PathVariable("size")int size){
		String str = begintime+""+endtime+""+page;
		PageBean bean =service.findByDate(begintime,endtime, page, size,str);
		return bean;
	}
	
	@GetMapping("/complaint/{id}")
	public ComplaintBean findById(@PathVariable("id")int id) {
		ComplaintBean bean = service.findById(id);
		return bean;
	}
	
	@PutMapping("/complaint/{id}/{type}")
	public int updateComplaint(@PathVariable("id")int id,@PathVariable("type")int type) {
		service.updateComplaint(id,type);
		return 1;
		
	}
	
	@DeleteMapping("/complaint/{id}")
	public int deleteComplaint(@PathVariable("id")int id) {
		service.deleteComplaint(id);
		return 1;
	}
	
	@PutMapping("/complaint/{id}")
	public int updateCreOrder(@PathVariable("id")int id) {
		service.updateOrder(id);
		return 1;
	}

	@PutMapping("/complaint/statusType/{id}")
	public int updateStatusType(@PathVariable("id")int id) {
		service.updateStatusTpe(id);
		return 1;
	}
	
}
