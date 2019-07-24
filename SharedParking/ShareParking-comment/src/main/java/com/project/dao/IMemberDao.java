package com.project.dao;

import com.project.Bean.MemberBean;
import org.apache.ibatis.annotations.Update;

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
        通过姓名查询
     */
    MemberBean findByName(String name);

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
}
