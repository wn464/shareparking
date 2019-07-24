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
    private MarkBean member;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MarkBean getGender() {
        return gender;
    }

    public void setGender(MarkBean gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public MarkBean getMember() {
        return member;
    }

    public void setMember(MarkBean member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", job='" + job + '\'' +
                ", member=" + member +
                '}';
    }
}
