package com.wd.bigdata.javaexample.proxy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;

/**
 * Created by yanghuanqing@wdai.com on 30/06/2017.
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String,Student> map = new HashMap();
        map.put("yang",new Student("yang",30));
        map.put("huan",new Student("huan",30));
        map.put("yang",new Student("yang",30));
        System.out.println(map.get("yang"));
        System.out.println(map.get("huan"));
    }
}
@Data
@AllArgsConstructor
@ToString
class  Student{

    private String name;
    private int age;
}