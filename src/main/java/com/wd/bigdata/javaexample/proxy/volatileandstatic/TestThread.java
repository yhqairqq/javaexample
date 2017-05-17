package com.wd.bigdata.javaexample.proxy.volatileandstatic;

/**
 * Created by Peter on 17/05/2017.
 */
public class TestThread implements Runnable{


    public TestThread(String name) {
        this.name = name;
    }

    private volatile int i = 0;

    private String name;

    public void run() {

        while(true){
            System.out.println(name+":"+i++);
        }

    }
}
