package com.ako.example.jdk.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuanqing@wdai.com on 05/07/2017.
 */

/**
 * 应用场景：可以针对bean进行proxy，做一些相关的统计
 */
@Component
public class CustomizationBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization  Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {



        System.out.println("postProcessAfterInitialization  Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }
}
