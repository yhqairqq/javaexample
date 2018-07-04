package com.ako.example.jdk.concurrent;

import com.alibaba.dubbo.common.threadpool.support.AbortPolicyWithReport;
import com.alibaba.dubbo.common.utils.NamedThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 05/09/2017.
 */
public class FutureTest {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10,
            20,
            0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new NamedThreadFactory("线程池"),
            new AbortPolicyWithReport("线程池",null)
    );

    public void process() {

        for(int k=0;k<20;k++){
            List<Future<String>> futures = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                futures.add(threadPoolExecutor.submit(new TaskS()));
            }
            for (int j = 0; ; j++) {
                Future<String> future = futures.get(j % futures.size());
                if (future.isDone()) {
                    try {
                        System.out.println("get response:"+future.get());
                        future.cancel(true);

                        futures.clear();
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("当前存活线程数量："+threadPoolExecutor.getActiveCount());
            System.out.println("继续主线程1s");
            try {
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        FutureTest test = new FutureTest();
        test.process();
    }
}


class TaskS implements Callable<String> {
    private final Random seed = new Random();
    @Override
    public String call() {
//        System.out.println(Thread.currentThread().getName());
        try {
            int r = seed.nextInt(3);
            Thread.sleep(r * 1000);
            System.out.println(Thread.currentThread().getName()+"休息:"+r+"秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName();
    }
}
