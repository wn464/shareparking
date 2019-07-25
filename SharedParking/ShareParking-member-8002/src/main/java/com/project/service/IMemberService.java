package com.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.project.Bean.MemberBean;

public interface IMemberService {
	
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
    List<MemberBean> findAll();
}
