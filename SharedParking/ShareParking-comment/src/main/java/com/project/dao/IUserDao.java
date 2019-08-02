package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.project.Bean.UserBean;

/*
    后台用户dao
 */
public interface IUserDao {
    /*
        通过用户名查找
     */
    UserBean findByUserName(String username);

    /*
     查询所有管理员
     id是为了 超级管理员查询所有管理员时 不显示自己
    */
    
    List<UserBean> findAll(@Param("id") int id);

    /*
        通过id查询管理员
     */
    UserBean findById(int id);

    /*
        添加管理员
     */
    @Insert("insert into user(username,password,name,img,gender,phonenumber,email,a_id) values(#{username},#{password},#{name},#{img},#{gender.id},#{phonenumber},#{email},#{authority.id})")
    int addUser(UserBean user);

    /*
    修改管理员权限
 */
    @Update("update user set a_id=#{a_id} where id=#{id}")
    int update(@Param("id")int id,@Param("a_id")int a_id);

    /*
       修改密码
     */
    @Update("update user set password=#{repassword} where id = #{id}")
    int updatePassword(@Param("id")int id,@Param("repassword")String repassword);
    /*
        修改手机号
     */
    @Update("update user set phonenumber=#{phone} where id =#{id}")
    int updatePhone(@Param("id")int id,@Param("phone")String phone);
    /*
     * 删除管理员
     */
    @Delete("delete from user where id =#{id}")
    int del(int id);

}
