package com.project.service;

import com.project.Bean.UserBean;

import java.util.List;

public interface IUserService {
    /*
       通过用户名查找
    */
    UserBean findByUserName(String username);
    /*
     查询所有管理员
     id是为了 超级管理员查询所有管理员时 不显示自己
    */
    List<UserBean> findAll(int id);
    /*
       修改密码
     */
    int updatePassword(int id,String repassword);
    /*
        修改手机号
     */
    int updatePhone(int id,String phone);
    /*
        修改管理员权限
     */
    int update(int id,int a_id);
}
