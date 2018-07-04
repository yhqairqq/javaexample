package com.ako.example.jdk.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by yanghuanqing@wdai.com on 23/06/2017.
 */
public class TestAtomicIntegerFieldUpdater {
    /**
     * @param AtomicIntegerFieldUpdater:原子更新整形的字段的更新器
     * @author yangcq
     */

    // 创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater
            = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public static void main(String[] args) {

        // 设置age的初始值为1000
        User user = new User();
        user.setUserName("yangcq");
        user.setAge(1000);

        // 原子更新引用数据类型的字段值
        System.out.println(atomicIntegerFieldUpdater.getAndIncrement(user));
        // 更新以后的值
        System.out.println(atomicIntegerFieldUpdater.get(user));
    }

    //实体类User
    public static class User{
        private String userName;
        public volatile int age;

        // setter、getter方法
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
}
