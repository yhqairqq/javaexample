package com.wd.bigdata.javaexample.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Peter on 15/05/2017.
 * http://rejoy.iteye.com/blog/1627405
 */
public class CustomInvacationHandler implements InvocationHandler {


    //目标对象
    private Object target;

    public CustomInvacationHandler(UserService userService) {
        super();
        this.target = userService;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


       Object result =  method.invoke(target,args);

        return result;
    }

    public Object getPorxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),target.getClass().getInterfaces(),this);
    }
}
