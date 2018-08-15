package com.ako.example.jdk.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/15.
 */
public class CountLatchDownTest3 {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(3);
        for(int i = 0; i< 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":装备");
                    while(true){
                        try {
                            System.out.println(Thread.currentThread().getName()+"开始运行");
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        count.await();
        System.out.println("继续执行主线程");
    }




}
