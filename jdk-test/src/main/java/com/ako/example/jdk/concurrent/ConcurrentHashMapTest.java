package com.ako.example.jdk.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanghuanqing@wdai.com on 19/06/2017.
 */
public class ConcurrentHashMapTest {



    public static void main(String args[]){
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();

        concurrentHashMap.put("key1","value1");
        concurrentHashMap.put("key1","value11");
        concurrentHashMap.put("key3","value3");
        concurrentHashMap.get("key2");
        concurrentHashMap.get("key1");
        concurrentHashMap.size();
    }

}
