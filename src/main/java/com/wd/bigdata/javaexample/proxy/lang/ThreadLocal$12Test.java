package com.wd.bigdata.javaexample.proxy.lang;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanghuanqing@wdai.com on 30/06/2017.
 */
public class ThreadLocal$12Test {

    public static void main(String[] args) {
        Thread task1 = new Thread(new DateFormatTask());
        Thread task2 = new Thread(new DateFormatTask());

        task1.start();
        task2.start();
    }


    public static String threadSafeFormat(Date date) {
        DateFormat formatter = PerThreadFormatter.getDateFormatter();

        if (formatter == null) {
            throw new NullPointerException("空指针");
        }

        return formatter.format(date);
    }
}

class PerThreadFormatter {
    private static final ThreadLocal<SimpleDateFormat> dateFormatHandler
            = new ThreadLocal<SimpleDateFormat>();    //静态最终

    public static SimpleDateFormat getDateFormatter() {

        dateFormatHandler.set(new SimpleDateFormat("yyyyMMddHHmmss"));

        DateFormat  dateFormat = null;

        try{
             dateFormat = dateFormatHandler.get();
        }finally {
            dateFormatHandler.remove();    //显示调用释放空间的动作
        }

        System.out.println(Thread.currentThread().getName()+":"+dateFormatHandler);

        return (SimpleDateFormat) dateFormat;
    }
}

class DateFormatTask implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "" +
                    ":"
                    + ThreadLocal$12Test.threadSafeFormat(new Date()));
        }
    }
}
