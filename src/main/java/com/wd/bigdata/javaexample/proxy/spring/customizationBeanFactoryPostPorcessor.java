package com.wd.bigdata.javaexample.proxy.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuanqing@wdai.com on 05/07/2017.
 */

/**
 * 应用场景：可以修改bean的定义
 */
@Component
public class customizationBeanFactoryPostPorcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("this is postProcessBeanFactory");

    }
}
