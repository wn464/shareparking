package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.project.Bean.MemberBean;
import com.project.Bean.MessageBean;
/*
    前台用户 详细信息dao
 */
public interface IMessageDao {
    /*
        通过用户id查询
     */
    MessageBean findByMid(int id);
    
    /*
     * 名字模糊查询
     */
    List<MessageBean> findByMohu(String name);
    
    /*
     * 查询所有
     */
    List<MessageBean> findAll();
    
    /*
     * 修改个人信息
     */
   int updateMySelf(@Param("address")String address,@Param("job")String job,@Param("m_id")int m_id);
   
   /*
    *完善个人信息 
    */
   @Update("update message set name=#{name},idCard=#{idCard},address=#{address},job=#{job} where m_id=#{member.id}")
   int complete(MessageBean mess);
   
   /*
    *添加mess表
    */
   @Insert("insert into message(name,idCard,address,m_id,job) values(#{name},#{idCard},#{address},#{member.id},#{job})")
   int addMess(MessageBean mess);

}
