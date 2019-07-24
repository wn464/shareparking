package com.project.service;

import com.project.Bean.UserBean;
import feign.Param;

import java.util.List;

/*
    管理员 service
 */
public interface IUserService {
    List<UserBean> findAll(@Param("id") int id);
    UserBean findByUserName(String username);
    int updatePassword(int id,String repassword);
    int updatePhone(int id,String phone);
    int update(int id,int a_id);
    int addUser(UserBean user);
    UserBean findById(int id);
}
