package com.project.dao;

import com.project.Bean.AuthorityBean;

/*
    权限 dao
 */
public interface IAuthorityDao {

    AuthorityBean findById(int id);
}
