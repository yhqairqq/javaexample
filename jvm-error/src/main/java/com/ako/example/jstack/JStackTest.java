package com.ako.example.jstack;

import com.ako.example.Student;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/14.
 */
public class JStackTest {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        ThreadPoolExecutor executors = new ThreadPoolExecutor(
                4,
                8,
                100,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        Runnable[] tasks = new Runnable[5];
        for (int i = 0; i < 5; i++) {
            Student s = new Student("同学-" + i, lock);
            tasks[i] = new Runnable() {
                @Override
                public void run() {
                    s.walk();
                }
            };
            executors.execute(tasks[i]);
        }

    }


}
