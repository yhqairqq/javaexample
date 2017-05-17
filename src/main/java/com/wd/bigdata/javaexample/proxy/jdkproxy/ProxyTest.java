package com.wd.bigdata.javaexample.proxy.jdkproxy;

import org.junit.Test;

/**
 * Created by Peter on 15/05/2017.
 */
public class ProxyTest {


    @Test
    public void testProxy(){

        UserService userService = new UserServiceImpl();


        CustomInvacationHandler invacationHandler = new CustomInvacationHandler(userService);


        UserService proxy = (UserService)invacationHandler.getPorxy();


        proxy.add("yanghuanqing");

    }

    @Test
    public void testGeneratorProxyClass(){
        ProxyGeneratorUtils.writeProxyClass("/Users/YHQ/Downloads/$Proxy11.class");
    }
}
