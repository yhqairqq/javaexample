package com.ako.example.jdk.concurrent;

/**
 * Created by yanghuanqing@wdai.com on 16/06/2017.
 */
public class DynamicInstanceTest {


    public static void main(String args[]) throws ClassNotFoundException {

        System.out.println("init classload");
        Class classA$ClassLoader = new DynamicInstanceTest().getClass().getClassLoader().loadClass("com.wd.bigdata.javaexample.proxy.concurrent.ClassA");

        System.out.println(classA$ClassLoader.getClassLoader());

        System.out.println("init class for name");
        Class classA$Class = new DynamicInstanceTest().getClass().getClassLoader().loadClass("com.wd.bigdata.javaexample.proxy.concurrent.ClassA");
        System.out.println(classA$Class.getClassLoader());
    }
}
