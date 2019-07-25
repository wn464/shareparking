package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Bean.MemberBean;
import com.project.service.IMemberService;

@RestController
public class MemberHandler {
	@Autowired
	private IMemberService service;
	
	

	
}
