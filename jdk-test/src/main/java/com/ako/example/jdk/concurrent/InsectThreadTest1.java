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
 * Created by yanghuanqing@wdai.com on 29/07/2017.
 */
public class InsectThreadTest1 {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors()*2,
            100,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<Runnable>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public void start(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
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

        final Service2 service = new Service2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    service.get();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    service.set();
                }
            }
        });
        t1.start();
        t2.start();

    }

}
class Service2 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile  Boolean hasValue=false;
    private final AtomicLong counter = new AtomicLong(0);
    public void set(){
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+":获得锁");
            while(hasValue == true){
                System.out.println(Thread.currentThread().getName()+":等待条件");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+":条件满足");
            System.out.println(Thread.currentThread().getName()+":"+counter.getAndIncrement());
            System.out.println(Thread.currentThread().getName()+":将自己设置为等待条件");
            hasValue = true;
            System.out.println(Thread.currentThread().getName()+":通知对方");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void get(){
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+":获得锁");
            while(hasValue == false){
                System.out.println(Thread.currentThread().getName()+":等待条件");
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+":"+counter.getAndIncrement());
            System.out.println(Thread.currentThread().getName()+":条件满足");
            System.out.println(Thread.currentThread().getName()+":"+counter.getAndIncrement());
            System.out.println(Thread.currentThread().getName()+":将自己设置为等待条件");
            hasValue = false;
            System.out.println(Thread.currentThread().getName()+":通知对方");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


