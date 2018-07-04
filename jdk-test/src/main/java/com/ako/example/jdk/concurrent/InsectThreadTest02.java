package com.ako.example.jdk.concurrent;

/**
 * Created by yanghuanqing@wdai.com on 01/08/2017.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 01/08/2017.
 */


/**
 * Created by yanghuanqing@wdai.com on 29/07/2017.
 */
public class InsectThreadTest02 {


    volatile long  count = 0;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            100,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<Runnable>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
//                try{
//
//
//
//                }catch (InterruptedException e){
//
//                }finally {
//
//                }
            }
        });
    }

    public static void main(String[] args) {
        final Service3 service = new Service3();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<50) {
                    service.get1();
                    i++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<50) {
                    service.get2();
                    i++;
                }
            }
        });
        t1.start();
        t2.start();

    }

}

class Service3 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition canWrite = lock.newCondition();
    private Condition canRead = lock.newCondition();
    private volatile Boolean hasValue = true;
    private final AtomicLong counter = new AtomicLong(0);

    public void get2() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + ":获得锁");
            if (hasValue == true) {
                System.out.println(Thread.currentThread().getName() + ":等待条件");
                canWrite.await();
            }
            System.out.println(Thread.currentThread().getName() + ":条件满足");
            System.out.println(Thread.currentThread().getName() + ":" + counter.getAndIncrement());
            System.out.println(Thread.currentThread().getName() + ":将自己设置为等待条件");
            hasValue = true;
            System.out.println(Thread.currentThread().getName() + ":通知对方");
            canRead.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get1() {
        try {
            lock.lockInterruptibly();

            System.out.println(Thread.currentThread().getName() + ":获得锁");
            if (hasValue == false) {
                System.out.println(Thread.currentThread().getName() + ":等待条件");
                canRead.await();
            }
            System.out.println(Thread.currentThread().getName() + ":" + counter.getAndIncrement());
            System.out.println(Thread.currentThread().getName() + ":条件满足");
            System.out.println(Thread.currentThread().getName() + ":" + counter.getAndIncrement());
            System.out.println(Thread.currentThread().getName() + ":将自己设置为等待条件");
            hasValue = false;
            System.out.println(Thread.currentThread().getName() + ":通知对方");
            canWrite.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


