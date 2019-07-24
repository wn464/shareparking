package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.controller.interfaces.ITestHandler;

@RestController
public class TestHandler {

	@Autowired
	private ITestHandler testHandler;
	@GetMapping("/test")
	public String getTest() {
		return testHandler.getTest();
	}
}
