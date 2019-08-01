package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.Bean.MemberBean;
import com.project.Bean.MessageBean;
import com.project.dao.IMemberDao;
import com.project.dao.IMessageDao;
import com.project.service.IMemberService;
/**
 * member service
 * @author Administrator
 *
 */
@Service
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	private IMemberDao dao;
	
	@Autowired
	private IMessageDao dao1;
	/*
	 * 通过id查找
	 */
	@Cacheable(value="findById")
	@Override
	public MemberBean findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	
	/*
	 * 通过用户名查找
	 */
	@Override
	public MemberBean findByUserName(String username) {
		MemberBean bean = dao.findByUserName(username);
		return bean;
	}
	
	/*
	 * 修改手机号
	 */
	@CacheEvict(value= {"updatePhone"},allEntries = true)
	@Override
	public int updatePhone(int id, String phone) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * 修改密码
	 */
	@CacheEvict(value= {"updatePassword"},allEntries = true)
	@Override
	public int updatePassword(int id, String repassword) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * 查询所有
	 */
	@Cacheable("findAll")
	@Override
	public List<MessageBean> findAll() {
		// TODO Auto-generated method stub
		return dao1.findAll();
	}

	/*
	 * 模糊查找
	 */
	@Cacheable(value="findByMohu",key="#name")
	@Override
	public List<MessageBean> findByMohu(String name) {
		// TODO Auto-generated method stub
		
		return dao1.findByMohu(name);
	}
	
	/*
	 * 修改个人信息
	 */
	@Override
	public int updateMySelf(String address, String job, int m_id,String phone) {
		// TODO Auto-generated method stub
		int mySelf = dao1.updateMySelf(address, job, m_id);
		int phon = dao.updatePhone(m_id, phone);
		return 1;
	}

	@Override
	public int reg(MemberBean member) {
		// TODO Auto-generated method stub
		return dao.reg(member);
	}

	@Override
	public int complete(MessageBean mess) {
		// TODO Auto-generated method stub
		return dao1.complete(mess);
	}
	
	

}
