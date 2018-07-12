package com.ako.example.dubbo.nio;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public class ServerLauncher {
   static  ApplicationContext context = null;
    static{
        context= new ClassPathXmlApplicationContext("classpath*:server.xml"){
            @Override
            protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
                super.customizeBeanFactory(beanFactory);
                beanFactory.setAllowBeanDefinitionOverriding(false);
            }
        };
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Executors.defaultThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {
               while(true){
                   try {
                       Thread.sleep(1000);
                       System.out.println("心跳");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        });
        thread.setDaemon(true);
        thread.start();
        thread.join();
    }
}
