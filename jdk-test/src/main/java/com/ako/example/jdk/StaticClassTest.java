package com.ako.example.jdk;

/**
 * Created by yanghuanqing@wdai.com on 2018/5/22.
 */
public class StaticClassTest {
    public static void main(String[] args) {
        UserGeneerator userGeneerator = new UserGeneerator();
        UserGeneerator userGeneerator2 = new UserGeneerator();

        System.out.println(userGeneerator.createUser() == userGeneerator2.createUser());
        System.out.println(userGeneerator.createUser());
        System.out.println(userGeneerator2.createUser());


    }




}

class UserGeneerator{
    private static User user;
    public User createUser(){
        if(user == null){
            user = new User();
            return user;
        }
        return user;
    }
}

class User{
    private String name;
    private int age;
}
