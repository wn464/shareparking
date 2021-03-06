package com.project.controller;

import com.jayway.jsonpath.internal.function.json.Append;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
       // boolean mobileQuery = JuHeDemo.mobileQuery(phone, 169209, generateCode);
        return false;
    }

    @PostMapping("/user/username")
    public UserBean findByName(HttpSession session,String username) {
    	
    	if(session.getAttribute("userid")==null) {
    		return service.findByUserName(username);
    	}else {
    		int id = (int) session.getAttribute("userid");
        	UserBean bean = service.findById(id);

        	if(bean.getUsername()!=null) {
        		return service.findByUserName(bean.getUsername());
        	}
        	return service.findByUserName(username);
    	}
    	
    	
    }

    /*
        ------------------------查询模块----------------------
     */
    //查询所有管理员
    @GetMapping("/user/findall")
    public List<UserBean> findAll(HttpServletRequest request){	 
    	HttpSession session = request.getSession();
    	System.out.println(session.getId());
    	 int id = (int) session.getAttribute("userid");	//获取当前登录的id
    	 System.out.println("id======="+id);
        List<UserBean> list = service.findAll(id);
        System.out.println(list);
        return list;
    }
    
    //通过id查询管理员
    @GetMapping("/user/findById")
    public UserBean findById(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	int id = (int) session.getAttribute("userid");	//获取当前登录的id
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
            return 3;
        }else {
            //判断是否存在这个用户
            UserBean bean = service.findByUserName(user.getUsername());
            if(bean==null) {
                
                int i = service.addUser(user);

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
    @PostMapping("/user/updatePassword")
    public String updataPassword(HttpServletRequest request,String password, String repassword) {
    
    	HttpSession session = request.getSession();
    	int id = (int) session.getAttribute("userid");	//获取当前登录的id
           
           UserBean user = service.findById(id);

            //如果输入的原密码正确
            if(user.getPassword().equals(password)) {
                
                int num = service.updatePassword(id,repassword);		//如果旧密码匹配 执行修改密码sql

                return "1";					//1是修改成功
            }
            return "2";						//2是修改失败
        }

    

    /*
        修改手机号
        1:修改成功
        2：修改失败
     */
    @PutMapping("/user/updatePhone")
    public int updatePhone(HttpSession session,String code,String phone){
    	System.out.println(code);
    	System.out.println(generateCode.toString()+"================");
    	
        if(code.equals(generateCode.toString())){
           
            int id = (int) session.getAttribute("userid");
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
    public int updateAuthority(String username,Integer a_id){
    	UserBean bean = service.findByUserName(username);
    	System.out.println(a_id);
        int i = service.update(bean.getId(), a_id);
        return i;
    }
    
    /**
     * 删除管理员
     */
    @DeleteMapping("/user/del")
    public int delete(Integer id) {
    	
    	return service.del(id);
    }
}
