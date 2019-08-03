package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

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
    int updatePhone(@Param("id")int id,@Param("phone")String phone);

    /*
        修改密码
     */
    @Update("update member set password=#{repassword} where id=#{id}")
    int updatePassword(int id,String repassword);
    
    
    //message 查询用到的
    MemberBean findByMemberId(int id);
    
    /*
     * 注册
     */
    @Insert("insert into member(username,password,phonenumber) values(#{username},#{password},#{phonenumber})")
    int reg(MemberBean member);
   
   /*
    * 查询所有
    */
    @Select("select count(*) from member")
    int all();
}
