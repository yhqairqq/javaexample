package com.ako.example.jdk.concurrent;


import com.weidai.bigdata.utils.ReflectUtil;
import org.apache.log4j.helpers.ThreadLocalMap;

/**
 * Created by yanghuanqing@wdai.com on 06/09/2017.
 */
public class ThreadLocalTest {
    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public void add() {
        System.out.println(Thread.currentThread());
        threadLocal.set("threadLocal1 add");

    }
}

class ThreadLocal2 {

    public void add() {

        Thread concurrentThread = Thread.currentThread();

        Object o =  ReflectUtil.getFieldValue(concurrentThread,"threadLocals");


        System.out.println(o);
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        final ThreadLocal2 threadLocal2 = new ThreadLocal2();

        Thread t1 = new Thread(new Task1(threadLocalTest, threadLocal2));
        t1.start();
        t1.join();

    }
}

class Task1 implements Runnable {
    private ThreadLocalTest threadLocalTest;
    private ThreadLocal2 threadLocal2;

    public Task1(ThreadLocalTest threadLocalTest, ThreadLocal2 threadLocal2) {
        this.threadLocalTest = threadLocalTest;
        this.threadLocal2 = threadLocal2;
    }

    @Override
    public void run() {
        while (true) {
            threadLocalTest.add();
            threadLocal2.add();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
