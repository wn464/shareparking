package com.project.service.impl;

import com.project.Bean.UserBean;
import com.project.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    @Override
    public UserBean findByUserName(String username) {
        return null;
    }

    @Override
    public List<UserBean> findAll(int id) {
        return null;
    }

    @Override
    public int updatePassword(int id, String repassword) {
        return 0;
    }

    @Override
    public int updatePhone(int id, String phone) {
        return 0;
    }

    @Override
    public int update(int id, int a_id) {
        return 0;
    }
}
