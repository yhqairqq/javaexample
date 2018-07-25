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
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (true){
                   try {
                       System.out.println("用户线程");
                       Thread.sleep(10000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }).start();

    }
}
