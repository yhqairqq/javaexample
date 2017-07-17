package com.wd.bigdata.javaexample.proxy.concurrent;

/**
 * Created by yanghuanqing@wdai.com on 16/06/2017.
 */
public class ClassA {

    static{
        System.out.println("class A static块");
    }

    public static void staticPrint(){
        System.out.println("class a static print");
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void addAge(int add){
        this.age = this.age+add;
    }

    public ClassA(int age) {
        this.age = age;
    }

    public ClassA() {
    }
}
