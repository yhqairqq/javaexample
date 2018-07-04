package com.ako.example.jdk.concurrent;


import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by yanghuanqing@wdai.com on 24/07/2017.
 */
public class PriorityBlockingQueueTest {

    private final PriorityBlockingQueue<Task> taskQueue
            = new PriorityBlockingQueue<Task>(10,
            new PriorityComparator());


    public void initQueue() {

        Random random = new Random(10000);

        for (int i = 0; i < 10; i++) {
            int ran = random.nextInt();
            taskQueue.add(new Task(random.nextInt(), "Thread:" + ran));
        }
    }

    public PriorityBlockingQueueTest() {
        initQueue();
    }

    public void run() {
        Iterator<Task> taskIterator = taskQueue.iterator();
        while (taskIterator.hasNext()) {
            Task task = taskIterator.next();
            task.run();

        }
    }

    public static void main(String[] args) {
        new PriorityBlockingQueueTest().run();
    }


}

class PriorityComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {

        if (o1.getPriority() > o2.getPriority()) {
            return 1;
        } else {
            if (o1.getPriority() < o2.getPriority()) {
                return -1;
            } else {
                return 0;
            }
        }

    }
}


class Task implements Runnable {

    private int priority;

    private String ThreadName;

    public int getPriority() {
        return priority;
    }

    public Task(int priority) {
        this.priority = priority;
    }

    public Task(int priority, String threadName) {
        this.priority = priority;
        ThreadName = threadName;
    }


    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + ":" + ThreadName + "权重为:" + priority);

    }

}
