package com.ako.example.jdk.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yanghuanqing@wdai.com on 26/07/2017.
 */
public class CountLatchDownTest {
    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for(int i=0;i<nThreads;i++){
            Thread t = new Thread(){
                public void run(){
                    try{
                        startGate.await();
                        task.run();
                    }catch (InterruptedException ignored){

                    }finally {
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        endGate.countDown();
        return end-start;
    }

    public void doTest() throws InterruptedException {
        System.out.println("cost:"+timeTasks(10,new TimeTask()));;
    }

    public static void main(String[] args) {
        CountLatchDownTest countLatchDownTest = new CountLatchDownTest();
        try {
            countLatchDownTest.doTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
class TimeTask implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
