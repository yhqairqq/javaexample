package com.wd.bigdata.javaexample.proxy.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yanghuanqing@wdai.com on 03/06/2017.
 */
public class ConcurrentTest {

    public  static ExecutorService executorService  =  Executors.newFixedThreadPool(5);


    public static AtomicLong counter = new AtomicLong(0L);

    static class Job implements Runnable{
        private String name;

        public Job(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while(counter.get()<1000000L){
                counter.addAndGet(1L);
                System.out.println(name+"--->"+counter.get());
                if(counter.get() == 600441L){
                    System.out.println("抛出异常");
                    throw new RuntimeException("模拟出现异常,线程"+name);
                }
            }
        }
    }

    public static  void main(String args[]) throws ExecutionException, InterruptedException {

        List<Future> futures = new ArrayList<>();
       for(int i=0;i<5;i++){
           futures.add(executorService.submit(new ConcurrentTest.Job(""+i)));

       }
       for (Future future:futures){
           System.out.println( future.get());
           if(future.isDone()){
           }
       }
       executorService.shutdown();

    }
}
