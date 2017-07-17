package com.wd.bigdata.javaexample.proxy.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanghuanqing@wdai.com on 19/06/2017.
 */
public class ConcurrentHashMapTest {



    public static void main(String args[]){
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();

        for(int i=0;i<100;i++){
            concurrentHashMap.putIfAbsent(String.valueOf(i),String.valueOf(i));
        }
    }

}
