package com.wd.bigdata.javaexample.proxy.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Test;

/**
 * Created by Peter on 15/05/2017.
 */
public class CglibTest {

    @Test
    public void cglibTest(){

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/YHQ/Downloads/1");
        UserServiceProxy cglib  = new UserServiceProxy();
        UserService userService = (UserService)cglib.getInstance(new UserService());

        userService.add("yanghuanqing");
    }

    public static void main(String[] args){
        UserServiceProxy cglib  = new UserServiceProxy();
        UserService userService = (UserService)cglib.getInstance(new UserService());

        userService.add("yanghuanqing");
    }
}
