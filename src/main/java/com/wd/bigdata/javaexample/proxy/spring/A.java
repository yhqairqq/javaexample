package com.wd.bigdata.javaexample.proxy.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuanqing@wdai.com on 06/07/2017.
 */

/**
 *
 * 实现ApplicationContextAware接口，会被注册，然后再A实例化，也就是调用construct完成后，才会调用
 * 接口中setApplicationContext（ApplicationContext applicationContext）
 */

public class A implements ApplicationContextAware{


    public A(){
        System.out.println("call construction A");
    }


    static {
        System.out.println("A static init");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("初始化 setApplicationContext A");
    }
}
