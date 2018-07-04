package com.ako.example.jdk.concurrent;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 21/11/2017.
 */
public class ConcurrentWriteFile {

    private static final int CAPACITY = 20000;

    private ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(CAPACITY);

    private AtomicReference<String> filename = new AtomicReference<>();

    private AtomicReference<File> file = new AtomicReference<>();

    public void process() {

        String varname = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        filename.set(varname);
        file.set(new File(varname + ".txt"));

        QueueProducer queueProducer = new QueueProducer(messageQueue);
        QueueConsume queueConsume = new QueueConsume(filename, messageQueue, file, new ReentrantLock());
        ExecutorService executors = Executors.newFixedThreadPool(10);
        executors.execute(queueProducer);
        for (int i = 0; i < 4; i++) {
            executors.execute(queueConsume);
        }
        while(true){

        }

    }

    @Test
    public void test1() {
        ConcurrentWriteFile concurrentWriteFile = new ConcurrentWriteFile();

        concurrentWriteFile.process();
    }


}

//class MonitorThread implements Runnable{
//
//    private AtomicReference<String> name;
//    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
//    @Override
//    public void run() {
//
//    }
//}

class QueueConsume implements Runnable {

    private AtomicReference<String> name;
    private ArrayBlockingQueue<String> messageQueue;
    private AtomicReference<File> file;

    public QueueConsume(AtomicReference<String> name, ArrayBlockingQueue<String> messageQueue, AtomicReference<File> file, ReentrantLock reentrantLock) {
        this.name = name;
        this.messageQueue = messageQueue;
        this.file = file;
        this.lock = reentrantLock;
    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
    private AtomicBoolean writable = new AtomicBoolean(true);
    private ReentrantLock lock;

    @Override
    public void run() {
//        new Thread() {
//            public void run() {
//                while (true) {
//                    String current = format.format(new Date());
//                    if (!name.get().equals(current)) {
//                        writable.getAndSet(false);
//                        file.set(new File(current + ".txt"));
//                        name.getAndSet(current);
//                        writable.getAndSet(true);
//                    }
//                    try {
//                        Thread.sleep(60 * 1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();

        while (true) {
            String message = messageQueue.poll();
            if(message ==null){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (!writable.get()) {
                    lock.lock();
                    try {


                    } finally {
                        lock.unlock();
                    }
                }
                try {
                    FileUtils.write(file.get(), message, "UTF-8", true);
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {

                }


                System.out.println(Thread.currentThread().getName() + "：插入到文件:" + file.get().getName() + ",内容:" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class QueueProducer implements Runnable {
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    private ArrayBlockingQueue<String> messageQueue;

    public QueueProducer(ArrayBlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {

                String varMessage = Thread.currentThread().getName() + ":" + format.format(new Date());
                messageQueue.put(varMessage + "\r\n");
                System.out.println(Thread.currentThread().getName() + "：生产一条===>" + varMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
