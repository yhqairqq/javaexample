package com.ako.example.jdk.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuanqing@wdai.com on 06/07/2017.
 */
@Component
public class B extends A{
    static{
        System.out.println("B static init");
    }
    @Autowired
    private C c;
}
