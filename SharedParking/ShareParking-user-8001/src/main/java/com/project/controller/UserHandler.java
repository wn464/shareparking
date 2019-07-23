package com.project.controller;

import com.project.Bean.UserBean;
import com.project.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    管理员controller
 */
@RestController
public class UserHandler {

    @Autowired
    private IUserService service;


    /*
        ------ 登录 模块 -----
        0 : 前台输入的格式不正确
        2 ：密码输入错误
        1 ：登录成功
     */
    @GetMapping("/user/login")
    public int login(@Validated UserBean user, BindingResult result){
        if (result.hasErrors()){
            System.out.println("------------出现错误---------");
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError.getDefaultMessage());
            }
            return 0;
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
            if (!subject.isAuthenticated()){
                try {
                    subject.login(token);
                    System.out.println("------认证成功------");

                    //把id存到session
                    UserBean bean = service.findByUserName(user.getUsername());
                    Session session = subject.getSession();
                    session.setAttribute("id",bean.getId());

                    return 1;
                }catch (Exception e){
                    System.out.println("------认证失败------");
                    return 2;
                }
            }
        }
        return 1;
    }

    /*
        ------------------------查询模块----------------------
     */
    //查询所有管理员
    @GetMapping("/user")
    public List<UserBean> findAll(Integer id){
        List<UserBean> list = service.findAll(id);
        return list;
    }


    /*
        ------------------------修改模块-----------------------
     */
//    /*
//        修改密码
//     */
//    public int updatePassword(Integer id,String password,String repassword){
//        int i = service.updatePassword(id, password, repassword);
//        return i;
//    }

    /*
        修改手机号
     */


}
