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
	
	@GetMapping("/complaint")
    public PageBean findComplaint(int status,int page,int size){
		PageBean bean =service.findByStatus(status, page, size);
		return bean;
	}
	
	@PutMapping("/complaint/{id}")
	public int updateComplaint(@PathVariable("id")int id) {
		service.updateComplaint(id);
		return 1;
		
	}
	
	@DeleteMapping("/complaint/{id}")
	public int deleteComplaint(@PathVariable("id")int id) {
		service.deleteComplaint(id);
		return 1;
	}

}
