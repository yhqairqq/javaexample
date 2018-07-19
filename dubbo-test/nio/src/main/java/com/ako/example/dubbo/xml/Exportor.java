package com.ako.example.dubbo.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/19.
 */
public class Exportor {

    static ClassPathXmlApplicationContext context;
    static {
         context = new ClassPathXmlApplicationContext("classpath*:serverDubbo.xml");
    }
    public static void main(String[] args) {
        context.start();
    }
}
