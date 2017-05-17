package com.wd.bigdata.javaexample.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Peter on 15/05/2017.
 */
public class UserServiceProxy implements MethodInterceptor {


    private Object target;
    public Object getInstance(Object target){
        this.target=target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     *
     * @param o      被拦截方法的对象的实例
     * @param method 要被拦截的方法
     * @param objects 参数
     * @param methodProxy  表示要触发父类的方法对象
     * @return
     * @throws Throwable
     */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("transaction is beginning");

        methodProxy.invokeSuper(o,objects);
        System.out.println("transaction is finished");
        return null;
    }
}
