package com.ako.example.jdk.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/15.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(2);

        for (int i = 0; i < 4 ; i++) {
            new Thread(
                    ()->{
                        System.out.println(Thread.currentThread().getName()+"：开始运行");
                        try {
                            barrier.await();
                            System.out.println(Thread.currentThread().getName()+"：结束运行");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
            ).start();
        }
        System.out.println("主线程结束");


    }
}
