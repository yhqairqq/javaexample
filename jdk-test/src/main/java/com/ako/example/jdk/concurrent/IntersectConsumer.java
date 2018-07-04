package com.ako.example.jdk.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 04/07/2017.
 */
public class IntersectConsumer {

    private static volatile int count = 0;

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition notEmpty = lock.newCondition();

    private static final Condition notFull = lock.newCondition();

    private static final AtomicInteger counter = new AtomicInteger(0);

    private static final Semaphore semaphore = new Semaphore(1);

    static class Task implements Runnable{


        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                try {
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName()+":"+count);

                    count ++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaphore.release();
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task());
        Thread t2 = new Thread(new Task());
        t1.start();
        t2.start();
    }

}
