package com.wd.bigdata.javaexample.proxy.concurrent;

import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 16/06/2017.
 */
public class CountDownLatchTest {
    public static  void main(String args[]) throws InterruptedException {


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8,// 核心线程数
                6, // 最大线程数
                100, // 闲置线程存活时间
                TimeUnit.MILLISECONDS,// 时间单位
                new LinkedBlockingDeque<Runnable>(),// 线程队列
                Executors.defaultThreadFactory(),// 线程工厂
                new ThreadPoolExecutor.AbortPolicy()// 队列已满,而且当前线程数已经超过最大线程数时的异常处理策略
        );
        int count = 10;

        final CountDownLatch latch = new CountDownLatch(count);
        int[] datas = new int[10204];
        int step = datas.length/count;
        for(int i=0;i<count;i++){
            int start = i*step;
            int end = (i+1)*step;
            if(i == count -1) end = datas.length;

            threadPool.execute(new MyRunnable(latch,datas,start,end));

        }
        latch.wait();
    }


}
