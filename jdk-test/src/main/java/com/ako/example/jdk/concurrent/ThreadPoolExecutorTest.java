package com.ako.example.jdk.concurrent;

import org.junit.Test;
import org.omg.SendingContext.RunTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 2018/3/27.
 */

public class ThreadPoolExecutorTest {

    @Test
    public void test1() throws InterruptedException {
        Thread thread = Executors.defaultThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":你好," + Thread.currentThread().getThreadGroup());
                }
            }
        });
        thread.start();
        thread.join();


    }

    @Test
    public void test2() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4,
                4,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        for(int i=0;i<100;i++){
            Future<String> future =   threadPoolExecutor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    TimeUnit.SECONDS.sleep(20);
                    return "成功";
                }
            });
        }
        Thread monitor = Executors.defaultThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "[线程池大小]:" + threadPoolExecutor.getActiveCount());
                    System.out.println(Thread.currentThread().getName() + "[线程池大小]:" + threadPoolExecutor.getQueue().size());
                }
            }
        });

        monitor.setDaemon(true);
        monitor.start();
        monitor.join();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                threadPoolExecutor.shutdown();
            }
        }));

    }

    @Test
    public void test3() throws InterruptedException {
       Thread thread =  Executors.defaultThreadFactory().newThread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    List<String> arr = new ArrayList<>();
                    Runnable task = new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(arr.size());
                        }
                    };
                    System.out.println(task);
                }
            }
        });

        thread.start();
        thread.join();
    }
}

class A {
    public <T> List<T> get(Runnable run, T result) {
        return new ArrayList<T>();
    }
}
