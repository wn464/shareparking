package com.project.dao;

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
        查询所有用户信息
     */
}
