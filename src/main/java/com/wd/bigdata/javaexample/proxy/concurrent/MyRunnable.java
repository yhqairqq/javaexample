package com.wd.bigdata.javaexample.proxy.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yanghuanqing@wdai.com on 16/06/2017.
 */
public class MyRunnable implements Runnable{
    private CountDownLatch latch;
    private int[] data;
    private int start;
    private int end;

    public MyRunnable(CountDownLatch latch, int[] data, int start, int end) {
        this.latch = latch;
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        //数据排序
        latch.countDown();

    }
}
