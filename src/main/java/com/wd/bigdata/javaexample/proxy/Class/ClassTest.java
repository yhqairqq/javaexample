package com.wd.bigdata.javaexample.proxy.Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Peter on 17/05/2017.
 */
public class ClassTest {


    public static  void main(String args[]) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Class clazz1 = A.class;

        System.out.println(clazz1.newInstance().getClass().getSimpleName());


        Constructor con = Class.forName("com.wd.bigdata.javaexample.proxy.Class.A").getConstructor(String.class,int.class,float.class);

        Object o = con.newInstance("yang",29,0.2f);

        System.out.println(o.getClass().getName());
        System.out.println(o.toString());
    }
}
