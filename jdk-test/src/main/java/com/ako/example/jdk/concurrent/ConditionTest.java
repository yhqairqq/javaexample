package com.ako.example.jdk.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/26.
 */
public class ConditionTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread task1 = Executors.defaultThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    try {
                       condition1.await();
                        System.out.println(Thread.currentThread().getName());
                       condition2.signal();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }

            }
        });
        Thread task2 = Executors.defaultThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try{
                        condition1.signal();
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }
            }
        });
        task1.start();
        task2.start();
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
