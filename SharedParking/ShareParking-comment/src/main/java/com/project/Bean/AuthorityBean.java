package com.project.Bean;

import java.io.Serializable;

import lombok.Data;

/*
    权限bean
 */
@Data
public class AuthorityBean implements Serializable {
    private int id;
    private String name;
    private String message;

}
