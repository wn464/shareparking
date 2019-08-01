package com.project.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.project.controller.interfaces.LogRemoter;
import com.project.service.IComplaintService;

@RestController
public class ComplaintController {
	@Autowired
	private IComplaintService service;
	@Autowired
	private LogRemoter service1;
	
	@PostMapping("/complaint")
	public int addComplaint(@RequestBody ComplaintBean bean,HttpServletRequest req) {
		service.addComplaint(bean);
		HttpSession session = req.getSession(false);
        String membername = (String) session.getAttribute("membername");
        String message ="添加投诉";
        service1.insertLog(membername, message);
		return 1;
	}
	
	@GetMapping("/complaint/user/{id}")
	public List<ComplaintBean> findBymmid(@PathVariable("id")int id){
		List<ComplaintBean> list=service.findBymid(id);
		return list;
	}
	
	@GetMapping("/complaint/memberid")
	public int findByMemberid(HttpServletRequest req){
		HttpSession session = req.getSession(false);
        int memberid = (int) session.getAttribute("memberid");
		return memberid;
	}
	
	@GetMapping("/complaint/{begintime}/{endtime}")
	public int findDateNum(@PathVariable("begintime")String begintime, @PathVariable("endtime")String endtime) {
		String str = begintime+""+endtime;
		int i =service.findDateNum(begintime, endtime, str);
		return i;
	}
	
	@GetMapping("/complaint/order/{oid}/{mid}")
	public ComplaintBean findByOrder(@PathVariable("oid")int oid,@PathVariable("mid")int mid) {
		String str = oid+""+mid;
		ComplaintBean bean = service.findByoid(oid,mid,str);
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
		System.out.println("sdads"+bean);
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
	public int updateStatusType(@PathVariable("id")int id,HttpServletRequest req) {
		service.updateStatusTpe(id);
		HttpSession session = req.getSession(false);
        String membername = (String) session.getAttribute("membername");
        String message ="取消投诉";
        service1.insertLog(membername, message);
		return 1;
	}
	
}
