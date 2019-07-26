package com.project.dao;

import com.project.Bean.UserBean;
import feign.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
    int update(int id,int a_id);

    /*
       修改密码
     */
    @Update("update user set password=#{repassword} where id = #{id}")
    int updatePassword(int id,String repassword);
    /*
        修改手机号
     */
    @Update("update user set phonenumber=#{phone}")
    int updatePhone(int id,String phone);


}
