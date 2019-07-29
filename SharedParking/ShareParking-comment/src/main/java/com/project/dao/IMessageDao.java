package com.project.dao;

import java.util.List;

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
   int updateMySelf(String address,String job,int m_id);
}
