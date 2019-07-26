package com.project.Bean;

import java.io.Serializable;

import lombok.Data;
/*
    前台用户个人信息表
 */
@Data
public class MessageBean implements Serializable{
    private int id;
    private String name;
    private String idCard;
    private String address;
    private MarkBean gender;
    private String job;
    private MemberBean member;


}
