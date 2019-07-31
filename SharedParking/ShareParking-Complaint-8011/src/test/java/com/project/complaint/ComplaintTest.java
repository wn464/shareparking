package com.project.complaint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.Bean.ComplaintBean;
import com.project.Bean.MemberBean;
import com.project.Bean.OrderBean;
import com.project.Bean.PageBean;
import com.project.demo.ApplicationDemo8011;
import com.project.service.IComplaintService;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ApplicationDemo8011.class)
public class ComplaintTest {
	@Autowired
	private IComplaintService service;
	@Test
	public void test1() {
		int i = service.findComNum(12);
		System.out.println(i);
	}
	@Test
	public void test2() {
		ComplaintBean bean = new ComplaintBean();
		MemberBean bean1 = new MemberBean();
		MemberBean bean3 = new MemberBean();
		bean1.setId(1);
		bean3.setId(2);
		OrderBean bean2 = new OrderBean();
		bean2.setId(1);
		bean.setComtime("2019-07-23 11:00:21");
		bean.setImgName("1.jpg");
		bean.setMessage("hao");
		bean.setMem_j_id(bean1);
		bean.setMem_y_id(bean3);
		bean.setO_id(bean2);
		service.addComplaint(bean);
		
	}
	@Test
	public void test3() {
		service.deleteComplaint(2);
	}
//	@Test
//	public void test4() {
//		service.updateComplaint(2);
//	}
//	@Test
//	public void test5() {
//		PageBean bean = service.findByStatus(12, 1, 2);
//		System.out.println("页码："+bean);
//	}
//	@Test
//	public void test6() {
//		PageBean bean = service.findByDate("2019-07-22 00:00:00","2019-07-25 00:00:00", 1, 2);
//		System.out.println("页码："+bean);
//	}
//	@Test
//	public void test7() {
////		int i = service.findDateNum("2019-07-22 00:00:00","2019-07-25 00:00:00");
//		System.out.println(i);
//	}
}
