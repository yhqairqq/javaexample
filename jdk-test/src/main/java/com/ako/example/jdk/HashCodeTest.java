package com.ako.example.jdk;

import org.junit.Test;

/**
 * Created by yanghuanqing@wdai.com on 2018/5/30.
 */
public class HashCodeTest {

    @Test
    public void test1(){
        String name1 = "goods_base_info";
        int hashcode = name1.hashCode();
        hashcode = Math.abs(hashcode);
        System.out.println(hashcode&(9-1));
        System.out.println(hashcode%(9-1));
    }
}
