package com.ako.example.jdk.concurrency;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by yanghuanqing@wdai.com on 09/01/2018.
 */
public class Task extends RecursiveAction{
    private static final long serialVersionUID = -2539978384180273213L;

    private List<Product>  products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if(last - first < 10 ){
            updatePrices();
        }else{
            int middle = (last+first)/2;
            System.out.println("Task Pending tasks; %s\n ");
            Task task1 = new Task(products,first,middle+1,increment);
            Task task2 = new Task(products,middle+1,last,increment);
            invokeAll(task1,task2);
        }
    }


    private void updatePrices(){
        for(int i=first;i<last;i++){
            Product product = products.get(i);
            product.setPrice(product.getPrice()*(1+increment));
        }
    }
}
