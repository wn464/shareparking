package com.project.dao;

import com.project.Bean.MemberBean;

/*
    前台用户 dao
 */
public interface IMemberDao {
    MemberBean findById(int id);
}
