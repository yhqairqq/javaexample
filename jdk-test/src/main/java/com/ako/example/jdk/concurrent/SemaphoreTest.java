package com.ako.example.jdk.concurrent;

import java.util.concurrent.Semaphore;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/15.
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {


        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 4; i++) {

            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(true){
                System.out.println(Thread.currentThread().getName()+":启动");
            }})
                    .start();

        }


        System.out.println("主线程结束");


    }

}
