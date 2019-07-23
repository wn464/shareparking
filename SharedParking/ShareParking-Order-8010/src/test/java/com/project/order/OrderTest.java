package com.project.order;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.Bean.CarportBean;
import com.project.Bean.MarkBean;
import com.project.Bean.MemberBean;
import com.project.Bean.OrderBean;
import com.project.Bean.PageBean;
import com.project.demo.ApplicationDemo;
import com.project.service.IOrderService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ApplicationDemo.class)
public class OrderTest {
	    @Autowired
	    private IOrderService orderService;
	    
	    @Test
	    public void insert() {
	    	System.out.println("===========================================");
	    	OrderBean orderBean = new OrderBean();
	    	orderBean.setBeginTime("2019-07-22 18:53:37").setEndTime("2019-07-22 18:53:41").setCarNumber("111");
	    	CarportBean carportBean = new CarportBean();
	    	carportBean.setId(1);
	    	orderBean.setCarportBean(carportBean);
	    	MemberBean memberBean1 = new MemberBean();
	    	memberBean1.setId(1);
	    	MemberBean memberBean2 = new MemberBean();
	    	memberBean2.setId(2);
	    	orderBean.setMemberBean1(memberBean1);
	    	orderBean.setMemberBean2(memberBean2);
	    	MarkBean delState = new MarkBean();
	    	delState.setId(6);
	    	orderBean.setDelState(delState);
	    	orderBean.setPrice(120);
	    	MarkBean statusBean = new MarkBean();
	    	statusBean.setId(3);
	    	orderBean.setStatusBean(statusBean);
	   
		}
	

}