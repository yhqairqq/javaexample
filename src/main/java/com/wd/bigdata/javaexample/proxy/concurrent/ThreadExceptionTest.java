package com.wd.bigdata.javaexample.proxy.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yanghuanqing@wdai.com on 03/06/2017.
 */
public class ThreadExceptionTest {


    public static void main(String args[]) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                long i=0L;
                while(i<666666){
                    i++;
//                    System.out.println(Thread.currentThread().getName()+"-->"+i);
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                long i=0L;
                while(i<666666){
                    i++;
                    if(i == 56781){
                        System.out.println("抛出异常");
                         System.out.println(Thread.currentThread().getName()+"-->"+i);
                        throw new RuntimeException("异常");
                    }

                }
            }
        };

        List<Future>  futures = new ArrayList<>();
        for (int i=0;i<5;i++){
            if(i%2==0){
                futures.add(executorService.submit(runnable1));
            }else{
                futures.add(executorService.submit(runnable2));
            }
        }
        for(Future future:futures){
            System.out.println(future.isDone());
            System.out.println(future.get());
        }

//        executorService.shutdown();
    }

}
