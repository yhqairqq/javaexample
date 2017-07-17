package com.wd.bigdata.javaexample.proxy.util;

/**
 * Created by yanghuanqing@wdai.com on 30/06/2017.
 */
public class HashCodeTest {
    public static void main(String[] args) {
        int i  = "yang".hashCode();
        System.out.println(i);

        System.out.println(i+">>>16     ="+( "yang".hashCode()>>>16));
        int off = i^(i>>>16);
        System.out.println(off);
    }
}
