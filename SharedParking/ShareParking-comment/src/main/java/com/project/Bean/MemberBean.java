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
    private CredibilityBean credibility;   //信誉度

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public CredibilityBean getCredibility() {
        return credibility;
    }

    public void setCredibility(CredibilityBean credibility) {
        this.credibility = credibility;
    }

    @Override
    public String toString() {
        return "MemberBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", message=" + message +
                ", credibility=" + credibility +
                '}';
    }
}
