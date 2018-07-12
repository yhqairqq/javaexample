package com.ako.example.spring;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/11.
 */
public class AutoWireCapbleBean {

    private static ApplicationContext context;
    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("classpath*:beans.xml"){
            @Override
            protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
                super.customizeBeanFactory(beanFactory);
                beanFactory.setAllowBeanDefinitionOverriding(false);
            }
        };
        NodeTask task = new NodeTask();
        autowire(task);
        task.init();
        NodeTask taskBean = context.getBean(NodeTask.class);
        System.out.println(taskBean);

    }


    public static void autowire(Object obj){
        context.getAutowireCapableBeanFactory().autowireBeanProperties(obj,
                AutowireCapableBeanFactory.AUTOWIRE_BY_NAME,
                false);
    }


}
