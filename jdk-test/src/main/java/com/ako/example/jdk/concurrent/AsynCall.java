package com.ako.example.jdk.concurrent;

import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 02/08/2017.
 */
public class AsynCall {

    private final ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(1,
                    2,
                    3000,
                    TimeUnit.MICROSECONDS,
                    new LinkedBlockingDeque<Runnable>(),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

    public String task() throws InterruptedException {
        Thread.sleep(2000);

        return "wait 2 secs" +
                "";
    }

    public void doCall() {
        Future<String> future = threadPoolExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return task();
            }
        });

        try {
            String result = future.get(3, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (TimeoutException var01) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AsynCall call = new AsynCall();
        call.doCall();
    }
}
