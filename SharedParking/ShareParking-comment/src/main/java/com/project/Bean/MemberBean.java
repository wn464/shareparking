package com.project.Bean;

import lombok.Data;

import java.io.Serializable;

//前台用户登录bean
@Data
public class MemberBean implements Serializable {
    private int id;
    private String username;
    private String password;
    private String phonenumber;
    private MessageBean message;
}
