package com.ako.example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/4.
 */
@Order(3)
public class SpringLoadOrderBean implements BeanPostProcessor,BeanFactoryPostProcessor,InitializingBean,DisposableBean,ApplicationContextInitializer,ApplicationListener {

    private int count = 0;
    public SpringLoadOrderBean() {
        System.out.println(++count+" SpringLoadOrderBean construction ");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println(++count+" DisposableBean.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(++count+" InitializingBean.afterPropertiesSet");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(++count+" BeanFactoryPostProcessor.postProcessBeanFactory");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(++count+" BeanPostProcessor.postProcessBeforeInitialization:bean "+bean+"  beanName:"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(++count+" BeanPostProcessor.postProcessAfterInitialization:bean "+bean+"  beanName:"+beanName);
        return bean;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println(++count+" ApplicationContextInitializer.init");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(++count+" ApplicationListener.applicationEvent:"+event);

    }
}
