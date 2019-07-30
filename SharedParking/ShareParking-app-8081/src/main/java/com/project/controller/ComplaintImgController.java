package com.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.util.imageutil;
@RestController
public class ComplaintImgController {
	@RequestMapping(value = "/comimg", method = {RequestMethod.POST})
public String upimage(@RequestParam(value="file",required=false) MultipartFile file,HttpServletRequest req) {
		System.out.println(file);
		String name=imageutil.Saveimg(file, req);
		System.out.println("照片"+name);
		
		    
	    return name;
}

}
