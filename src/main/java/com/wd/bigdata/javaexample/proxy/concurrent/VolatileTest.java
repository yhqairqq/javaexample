package com.wd.bigdata.javaexample.proxy.concurrent;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by yanghuanqing@wdai.com on 16/06/2017.
 */
public class VolatileTest {

    volatile int count=0;

    Hashtable <String,String> table = new Hashtable<>();

    public void addContent(String key,String value){
        if(count<100){
            table.put(key,value);
            count++;
        }
    }

    public void Test(){
        List<Runnable> threads = new ArrayList<>();

        for(int i=0;i<10;i++){
            threads.add(new Runnable() {

                @Override
                public void run() {
                    while (true) {

                        addContent("33"+count, "44444");
                        System.out.println(table.size());
                    }
                }
            });
        }
        for(Runnable task:threads){
            task.run();
        }

    }


    public static void main(String args[]){
        new VolatileTest().Test();

    }
}
