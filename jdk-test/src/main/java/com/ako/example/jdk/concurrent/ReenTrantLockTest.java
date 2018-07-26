package com.ako.example.jdk.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 04/07/2017.
 */
public class ReenTrantLockTest {

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notRead = lock.newCondition();

    private final Condition notWrite = lock.newCondition();

    private volatile int count = 0;

    public int getCount(){
        lock.lock();
        try {

            System.out.println(Thread.currentThread().getName() + ":" + count);
            return count++;

        } finally {
            lock.unlock();
        }
    }

    public static void main(String args[]){

        Runtime.getRuntime().addShutdownHook(Executors.defaultThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {

            }
        }));
        final ReenTrantLockTest counter = new ReenTrantLockTest();


         new Thread(new Runnable() {
            @Override
            public void run() {
                while(counter.getCount() < 6 ){

                }
            }
        }).start();
         new Thread(new Runnable() {
            @Override
            public void run() {
                while(counter.getCount() < 6 ){

                }
            }
        }).start();
    }


}
