package com.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Bean.ComplaintBean;
import com.project.Bean.PageBean;
import com.project.service.IComplaintService;

@RestController
public class ComplaintController {
	@Autowired
	private IComplaintService service;
	
	@PostMapping("/complaint")
	public int addComplaint(ComplaintBean bean) {
		service.addComplaint(bean);
		return 1;
	}
	
	@GetMapping("/complaint/{status}/{page}/{size}")
    public PageBean findComplaint(@PathVariable("status")int status,@PathVariable("page")int page,@PathVariable("size")int size){
		PageBean bean =service.findByStatus(status, page, size);
		return bean;
	}
	
	@GetMapping("/complaint/{begintime}/{endtime}/{page}/{size}")
    public PageBean findComplaintbydate(@PathVariable("begintime")String begintime, @PathVariable("endtime")String endtime, @PathVariable("page")int page, @PathVariable("size")int size){
		PageBean bean =service.findByDate(begintime,endtime, page, size);
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

}
