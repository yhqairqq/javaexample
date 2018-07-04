package com.ako.example.jdk.cpuused;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 12/12/2017.
 */
public class CpuUsedTest {

    public static void main(String[] args) throws InterruptedException {


        int AVAILABLE_CORES = Runtime.getRuntime().availableProcessors();

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor executors = new ThreadPoolExecutor(10, 10, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(20), new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 6; i++) {
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            });
        }
        executors.shutdown();
//        for(int i=0;i<10;i++){
//            new Thread(){
//                public void run(){
//                        while (true){
//                            System.out.println(Thread.currentThread().getName());
//                        }
//                }
//            }.start();
//        }
        System.out.println(Thread.currentThread().getName());
    }
}
