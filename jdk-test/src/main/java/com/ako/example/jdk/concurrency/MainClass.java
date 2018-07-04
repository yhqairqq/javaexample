package com.ako.example.jdk.concurrency;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanghuanqing@wdai.com on 09/01/2018.
 */
public class MainClass {

    public static void main(String[] args) {
        ProductListGenerator productListGenerator = new ProductListGenerator();
        List<Product> productList = productListGenerator.generate(10000);
        Task task = new Task(productList, 0, productList.size(), 0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        do {
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        pool.shutdown();
        if(task.isCompletedNormally()){
            System.out.printf("Main : The process has completed normally. \n");
        }


        for(int i=0;i<productList.size();i++){
            Product product = productList.get(i);
            if(product.getPrice() !=  12){
                System.out.printf("Product %s :%f\n ",product.getName(),product.getPrice());
            }
        }

        System.out.println("Main: End of the problem. \n");

    }

}
