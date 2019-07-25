package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.project.Bean.MemberBean;

/*
    前台用户 dao
 */
public interface IMemberDao {
    /*
        通过id查找
     */
    MemberBean findById(int id);

    /*
        通过用户名查找
     */
    MemberBean findByUserName(String username);

    /*
        修改手机号
     */
    @Update("update member set phonenumber = #{phone} where id=#{id}")
    int updatePhone(int id,String phone);

    /*
        修改密码
     */
    @Update("update member set password=#{repassword} where id=#{id}")
    int updatePassword(int id,String repassword);
    
    /*
     * 查找所有用户
     */
    List<MemberBean> findAll();
}
