package com.project.Bean;

import lombok.Data;

//前台用户登录bean
@Data
public class MemberBean {
    private int id;
    private String username;
    private String password;
    private String phonenumber;
    private MessageBean message;
}
