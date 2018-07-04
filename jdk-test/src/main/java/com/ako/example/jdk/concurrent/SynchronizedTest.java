package com.ako.example.jdk.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yanghuanqing@wdai.com on 08/08/2017.
 */
public class SynchronizedTest {

    private ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

    private AtomicLong counter = new AtomicLong(0);



    public void doWork(){
        task();


    }

    public void task(){
        while(true){
            long current = counter.get();
            long excep = current+1;
            if(!counter.compareAndSet(excep,current)){
                counter.set(excep);
                break;
            }
        }
    }

    public void doWork2(){
        task();
    }
    public void test(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    doWork();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                      doWork();
                  }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    doWork2();
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    doWork2();
                }
            }
        });

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println(counter.get());
    }

    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        synchronizedTest.test();
    }
}
