package com.project.service.serviceimpl;

import com.project.Bean.UserBean;
import com.project.dao.IUserDao;
import com.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
    管理员 serviceimpl
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao dao;
    /*
        查询所有管理员 不包括自己
     */
    @Override
    public List<UserBean> findAll(int id) {
        return dao.findAll(id);
    }

    /*
        通过用户名查找
     */
    @Override
    public UserBean findByUserName(String username) {
        return dao.findByUserName(username);
    }

    /*
        修改密码
     */
    @Override
    public int updatePassword(int id,String repassword) {

        return dao.updatePassword(id,repassword);
    }

    /*
        修改手机号
     */
    @Override
    public int updatePhone(int id, String phone) {
        return dao.updatePhone(id,phone);
    }

    /*
        修改管理员权限
     */
    @Override
    public int update(int id, int a_id) {
        return dao.update(id,a_id);
    }

    /*
    添加管理员
     */
    @Override
    public int addUser(UserBean user) {
        return dao.addUser(user);
    }

    /*
        通过id查询
     */
    @Override
    public UserBean findById(int id) {
        return dao.findById(id);
    }
}
