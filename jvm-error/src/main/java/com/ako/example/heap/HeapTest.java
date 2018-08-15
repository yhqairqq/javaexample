package com.ako.example.heap;

import com.ako.example.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/14.
 */
public class HeapTest {
    private static Map students = new HashMap();
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            if(++i % 10000 == 0){
                TimeUnit.SECONDS.sleep(1);
            }
            students.put(i,new Student("姓名"+i,new ReentrantLock()));
        }
    }
}
