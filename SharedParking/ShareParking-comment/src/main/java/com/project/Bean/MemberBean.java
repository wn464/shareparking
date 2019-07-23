package com.project.Bean;

import java.io.Serializable;

import lombok.Data;

//前台用户登录bean
@Data
public class MemberBean implements Serializable {
    private int id;
    private String username;
    private String password;
    private String phonenumber;
    private MessageBean message;
}
