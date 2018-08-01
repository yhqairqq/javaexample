package com.ako.example.mybatis;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/30.
 */
public class UserInfo implements Serializable{


    private static final long serialVersionUID = -9079620743544068086L;

    private long id;
    private String username;
    private String password;
    private int age;
    private SexEnum sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
