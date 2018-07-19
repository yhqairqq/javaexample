package com.ako.example.dubbo.xml;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/19.
 */
public class UserServiceImpl implements UserService{
    @Override
    public boolean login(String username) {
        System.out.println("welcome "+username);
        return false;
    }
}
