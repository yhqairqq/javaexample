package com.ako.example.jdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yanghuanqing@wdai.com on 29/06/2017.
 */
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new PlainTimerTask(),1L);
    }
}

class PlainTimerTask extends TimerTask{

    @Override
    public void run() {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
    }
}
