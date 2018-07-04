package com.ako.example.jdk.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * Created by yanghuanqing@wdai.com on 17/11/2017.
 */
public class EventBusExample {
    private AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    @Test
    public void testRegister(){
        eventBus.register(new Object(){

            @Subscribe
            public void listener(String name){
                System.out.println(name);
            }


            @Subscribe
            public void listenner(Integer age){
                System.out.println(age);
            }
            @Subscribe
            public void lister(DeadEvent event) {
                System.out.printf("%s=%s from dead events%n", event.getSource().getClass(), event.getEvent());
            }
        });

        eventBus.post("yang");
        eventBus.post(23);

        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){

                System.out.println("结束应用程序");
            }
        });
    }





}
