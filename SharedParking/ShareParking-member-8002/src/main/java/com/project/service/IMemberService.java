package com.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.project.Bean.MemberBean;
import com.project.Bean.MessageBean;

public interface IMemberService {
	
	/*
	 * 通过id查找
	 */
	MemberBean findById(int id);

    /*
        通过用户名查找
     */
    MemberBean findByUserName(String username);

    /*
        修改手机号
     */
    int updatePhone(int id,String phone);

    /*
        修改密码
     */
    int updatePassword(int id,String repassword);
    
    /*
     * 查找所有用户
     */
    List<MessageBean> findAll();
    /*
     * 模糊查询
     */
    List<MessageBean> findByMohu(String name);
    
    /*
     * 修改个人信息
     */
    int updateMySelf(String address,String job,int m_id,String phone);
    
    /*
     * 注册
     */
    int reg(MemberBean member);
    
    /*
     * 完善个人信息
     */
    int complete(MessageBean mess);
}
