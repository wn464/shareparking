package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Bean.MemberBean;
import com.project.Bean.MessageBean;
import com.project.Bean.UserBean;
import com.project.controller.interfaces.LogRemoter;
import com.project.service.IMemberService;

@RestController
public class MemberHandler {
	@Autowired
	private IMemberService service;
	@Autowired	
	private LogRemoter logservice;

	@GetMapping("/member/username")
	public MemberBean findByName(String username) {
		System.out.println(username);
		return service.findByUserName(username);
	}
	
	/*
	 * 查询所有用户
	 */
	@GetMapping("/member/findAll")
	public List<MessageBean> findAll(){
		List<MessageBean> list = service.findAll();

		return list;
	}
	/*
	 * 模糊查询
	 */
	@GetMapping("/member/findMohu")
	public List<MessageBean> findByMohu(String name) {
		List<MessageBean> list = service.findByMohu(name);
		return list;
	}
	
	/*
	 * 修改个人信息
	 * 1：修改成功
	 * 2：修改失败
	 */
	@PutMapping("/member/updatemyself/{phone}/{address}/{job}")
	public int updateMySelf(HttpSession session,@PathVariable("phone")String phone,@PathVariable("address")String address,@PathVariable("job")String job) {
		System.out.println(address);
		System.out.println(phone);
		int id = (int) session.getAttribute("memberid");
		int mySelf = service.updateMySelf(address, job,id, phone);
		if(mySelf!=0) {
			
			String name=(String) session.getAttribute("membername");
			String message=name+"修改个人信息";
			 logservice.insertLog(name, message); 
			 return 1;
		}
		return 2;			
	}
	
	/*
	 * 查看个人信息
	 */
	@GetMapping("/member/findbyid")
	public MemberBean findById(HttpSession session) {
		int id = 0;
		if(session.getAttribute("memberid")!=null) {
			id = (int) session.getAttribute("memberid");
		}
		System.out.println("id:"+id);
		MemberBean bean = service.findById(id);
		System.out.println(bean);
		return bean;
	}
	
	@GetMapping("/member/findid/{id}")
	public MemberBean findId(@PathVariable("id")Integer id) {
		MemberBean bean = service.findById(id);
		System.out.println(bean);
		return bean;
	}
	
	/*
	 * 注册
	 */
	@PostMapping("/member/reg")
	public int reg(HttpSession session,MemberBean member) {
		int i = service.reg(member);
		
		String name=(String) session.getAttribute("membername");
		String message=name+"注册了";
		 logservice.insertLog(name, message); 
		return i ;
	}
	
	/*
	 * 完善个人信息
	 */
	@PostMapping("/member/complete")
	public int complete(HttpSession session,MessageBean mess) {
	
		int id = (int) session.getAttribute("memberid");
	
		MemberBean member = new MemberBean();
		member.setId(id);
		mess.setMember(member);
		int i = service.complete(mess);
		String name=(String) session.getAttribute("membername");
		String message=name+"完善了个人信息";
		 logservice.insertLog(name, message); 
		return i;
	}
	
	/*
	 *查询用户数量
	 */
	@GetMapping("/member/all")
	public int all() {
		return service.all();
	}
}
