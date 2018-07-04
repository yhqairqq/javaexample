package com.ako.example.jdk.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 04/07/2017.
 */
public class ReenTrantLockTest2 {
    private static volatile int count = 0;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static final Condition condition2 = lock.newCondition();
    private static volatile boolean read = true;
    public static void doWork2() throws InterruptedException {

        lock.lockInterruptibly();

        if(count % 2 ==1){
            condition2.await();
        }

        try{
            System.out.println(Thread.currentThread().getName() + ":" + count);
            count++;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
    public static void doWork() throws InterruptedException {
            lock.lockInterruptibly();

            if(count %2 == 0)
                condition.await();

            try{
                System.out.println(Thread.currentThread().getName() + ":" + count);
                count++;
                condition2.signal();
            }finally {
                lock.unlock();
            }
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    try {
                        doWork2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<100;i++){
                    try {
                        doWork();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
