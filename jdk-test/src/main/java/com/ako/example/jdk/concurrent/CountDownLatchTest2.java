package com.ako.example.jdk.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yanghuanqing@wdai.com on 05/07/2017.
 */
public class CountDownLatchTest2 {



    static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                latch.countDown();
                System.out.println(2);
                latch.countDown();
            }
        }).start();

        latch.await();
        System.out.println(3);

    }






}
