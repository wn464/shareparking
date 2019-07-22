package com.project.dao;


import com.project.Bean.UserBean;
import org.apache.ibatis.annotations.Update;

/*
    管理员 dao接口
 */
public interface IUserDao {
    /*
        通过用户名查找
     */
    UserBean findByUserName(String username);

    /*、
        通过id查询
     */
    UserBean findById(int id);

    /*
        修改手机号
     */
    @Update("update user set phonenumber = #{phone} where id=#{id}")
    int updatePhoneNumber(String phone,int id);

    /*
        修改密码
     */
    @Update("update user set password=#{repassword} where id = #{id}")
    int updatePassword(String repassword,int id);


}
