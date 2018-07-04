package com.ako.example.jdk.aop;

import org.springframework.stereotype.Service;

/**
 * Created by yanghuanqing@wdai.com on 07/12/2017.
 */
@Service("student")
public class Student extends Person {
    @Override
    public void jump() {
        System.out.println("start jump");
    }
    @Override
    public void work() {
        System.out.println("start walk");
        jump();
    }


}
