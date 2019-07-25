package com.project.controller;

import com.project.Bean.MemberBean;
import com.project.Bean.UserBean;
import com.project.service.IUserService;
import com.project.util.CodeUtil;
import com.project.util.JuHeDemo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    管理员controller
 */
@RestController
public class UserHandler {
    private static StringBuffer  generateCode = null;

    @Autowired
    private IUserService service;

    /*
        ------- 发送短信----
     */
    @GetMapping("/member/send")
    public  boolean Send(String phone) {
        generateCode = CodeUtil.generateCode();
        System.out.println("--------"+generateCode.toString());
        boolean mobileQuery = JuHeDemo.mobileQuery(phone, 169209, generateCode);
        return mobileQuery;
    }

    /*
        ------ 登录 模块 -----
        0 : 前台输入的格式不正确
        2 ：密码输入错误
        1 ：登录成功
     */
    @PostMapping("/user/login")
    public int login(@Validated UserBean user, BindingResult result){
        if (result.hasErrors()){
            System.out.println("------------出现错误---------");
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError.getDefaultMessage());
            }
            return 0;
        }else{
        	Object obj = new SimpleHash("MD5",user.getPassword(),user.getUsername(),1024);
        	
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
    @GetMapping("/user/findall")
    public List<UserBean> findAll(Integer id){
        List<UserBean> list = service.findAll(id);
        return list;
    }
    //通过id查询管理员
    @GetMapping("/user/findById")
    public UserBean findById(Integer id){
        UserBean user = service.findById(id);
        return user;
    }


    /*
       ----------------------- 添加模块 -----------------------
     */
    //添加用户
    //1：添加成功
    //2：添加失败
    @PostMapping("/user/add")
    public int add(@Validated UserBean user,BindingResult result){
        if(result.hasErrors()) {
            System.out.println("----------出现错误----------");
            List<FieldError> list =result.getFieldErrors();
            for (FieldError error : list) {
                System.out.println(error.getDefaultMessage());
            }
            return 2;
        }else {
            //判断是否存在这个用户
            UserBean bean = service.findByUserName(user.getUsername());
            if(bean==null) {

                Object obj = new SimpleHash("MD5",user.getPassword(),user.getUsername(),1024);		//盐值

                UserBean user1 = new UserBean();									//创建user对象 然后封装
                user1.setUsername(user.getUsername());
                user1.setPassword(obj.toString());
                user1.setAuthority(user.getAuthority());
                int i = service.addUser(user1);

                return 1; // 注册成功  跳转注册成功页面
            }else {
                return 2;
            }

        }
    }

    //验证用户是否存在
    //1:可以注册
    //2：用户名存在不可以注册
    @GetMapping("/user/verify")
    public String verify(String username) {
        UserBean user = service.findByUserName(username);
        if(user!=null) {
            return "2";
        }
        return "1";
    }


    /*
        ------------------------修改模块-----------------------
     */
    /*
        修改密码
        1：修改成功
        2：修改失败
        3：前台输入是密码长度不合格
     */
    @PutMapping("/user/updatePassword")
    public String updataPassword(String password, String repassword) {

        if(repassword.length()<6||repassword.length()>12) {
            return "3";
        }else {
            Subject subject = SecurityUtils.getSubject();
            int id = (int) subject.getSession().getAttribute("id");	//获取当前登录的id

            UserBean user = service.findById(id);

            //将输入的原密码加密
            Object obj = new SimpleHash("MD5",password,user.getUsername(),1024);

            //如果输入的原密码正确
            if(user.getPassword().equals(obj.toString())) {
                //将输入的新密码加密
                Object obj1 = new SimpleHash("MD5",repassword,user.getUsername(),1024);
                int num = service.updatePassword(user.getId(),obj1.toString());		//如果旧密码匹配 执行修改密码sql

                return "1";					//1是修改成功
            }
            return "2";						//2是修改失败
        }

    }

    /*
        修改手机号
        1:修改成功
        2：修改失败
     */
    @PutMapping("/user/updatePhone")
    public int updatePhone(StringBuffer code,String phone){
        if(code.equals(generateCode)){
            Subject subject = SecurityUtils.getSubject();
            int id = (int) subject.getSession().getAttribute("id");
            int i = service.updatePhone(id, phone);
            return 1;
        }
        return 2;
    }

    /*
        修改管理员权限
        id：管理员的id
        a_id：级别的id
     */
    @PutMapping("/user/updateAuthority")
    public int updateAuthority(Integer id,Integer a_id){
        int i = service.update(id, a_id);
        return i;
    }

}
