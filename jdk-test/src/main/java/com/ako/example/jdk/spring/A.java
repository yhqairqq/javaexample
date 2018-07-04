package com.ako.example.jdk.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuanqing@wdai.com on 06/07/2017.
 */

/**
 *
 * 实现ApplicationContextAware接口，会被注册，然后再A实例化，也就是调用construct完成后，才会调用
 * 接口中setApplicationContext（ApplicationContext applicationContext）
 */
@Component
public class A implements ApplicationContextAware,ApplicationListener{



    @Value("${spring.test.a}")
    private String testVlaue;





    public A(){
        System.out.println("A构造函数");
    }


    static {
        System.out.println("A静态块");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("初始化 setApplicationContext A:"+testVlaue);
        System.out.println("初始化 setApplicationContext A");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(ContextRefreshedEvent.class.getName().equals(applicationEvent.getClass().getName())){
            System.out.println(" class A ContextRefreshedEvent");
        }

    }
}
