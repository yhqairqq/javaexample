package com.ako.example.jdk.concurrent;

import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 11/07/2017.
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {


        final LinkedBlockingDeque<String> workQueue = new LinkedBlockingDeque<String>();


        ScheduledExecutorService scheduledExecutorService
                = Executors.newScheduledThreadPool(
                        8
                );

        for (int i = 0; i < 10; i++) {
            workQueue.add(""+i);
        }
        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+":"+workQueue.poll());
                    }
                },
                1000,
                1000,
                TimeUnit.MICROSECONDS
        );

        for (int i = 10; i < 200; i++) {
            workQueue.add(""+i);
        }


//        scheduledExecutorService.schedule(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                String message = workQueue.poll();
//
//                System.out.println(Thread.currentThread().getName()+":"+message);
//                return Thread.currentThread().getName()+"";
//            }
//        },500, TimeUnit.MICROSECONDS);


    }
}
