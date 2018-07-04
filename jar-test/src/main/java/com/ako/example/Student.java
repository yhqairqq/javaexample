package com.ako.example;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/3.
 */
public class Student {

    private String id;
    private String name;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void say(){
        System.out.println("I am Y");
    }
}
