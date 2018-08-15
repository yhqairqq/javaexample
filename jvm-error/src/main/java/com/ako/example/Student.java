package com.ako.example;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/14.
 */
public class Student {
    private String name;
    private ReentrantLock lock;

    public Student(String name, ReentrantLock lock) {
        this.name = name;
        this.lock = lock;
    }

    public void walk() {
        while (true) {
            System.out.println(name + " work");
            lock.lock();
        }
    }
}