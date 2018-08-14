package com.ako.example.jdk.concurrent;

import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/8.
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                100,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 5 ; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("主线程");
                }
            }
        }).start();


    }
}
