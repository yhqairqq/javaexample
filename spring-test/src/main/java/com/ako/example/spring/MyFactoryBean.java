package com.ako.example.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/4.
 */
public class MyFactoryBean implements FactoryBean,InitializingBean,DisposableBean {


    private Object target;

    @Override
    public void destroy() throws Exception {
        target = null;
    }

    @Override
    public Object getObject() throws Exception {
        System.out.println("MyFactoryBean.getObject");
        return target;
    }

    @Override
    public Class getObjectType() {
        return Object.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        target = new Object();
    }
}
