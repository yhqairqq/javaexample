package com.ako.example.jdk.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuanqing@wdai.com on 06/07/2017.
 */
@Component
public class C {
    static{
        System.out.println("C static init");
    }

    @Autowired
    private A a;
    @Autowired
    private B b;
}
