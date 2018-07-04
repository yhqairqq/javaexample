package com.ako.example.jdk.Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanghuanqing@wdai.com on 17/01/2018.
 */
public class ThreadTest {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(new Task1(countDownLatch));
        Thread thread2 = new Thread(new Task2(countDownLatch));
        System.out.println(Thread.currentThread().getName() + " 正在运行");
        thread1.start();
        thread2.start();
        System.out.println("等待其他线程");
        countDownLatch.await();
        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread().getName() + "主线程退出");
    }
}


class Task1 implements Runnable {
    CountDownLatch countDownLatch;

    public Task1(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        int condition = 100;
        countDownLatch.countDown();
        while (--condition >= 0) {
            System.out.println("Task1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Task2 implements Runnable {
    private CountDownLatch countDownLatch;

    public Task2(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        int condition = 100;
        countDownLatch.countDown();
        while (--condition >= 0) {
            System.out.println("Task2");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
