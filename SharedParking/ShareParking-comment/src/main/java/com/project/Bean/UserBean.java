package com.project.Bean;

import lombok.Data;

/*
    后台用户bean
 */
@Data
public class UserBean {
    private int id;
    private String username;
    private String password;
    private String name;
    private String img;
    private String gender;
    private String phonenumber;
    private String email;
    private AuthorityBean authority;        //权限


}
