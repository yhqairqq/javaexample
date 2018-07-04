package com.ako.example.jdk.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanghuanqing@wdai.com on 26/07/2017.
 */
public class ConcurrentHashMapTest {


    public static void main(String[] args) {
        final ConcurrentHashMap<String,String>  map = new ConcurrentHashMap<>();
        map.put("1","2");

        String key =map.get("1");

        key = null;

        System.out.println(map.get("1"));

    }
}
