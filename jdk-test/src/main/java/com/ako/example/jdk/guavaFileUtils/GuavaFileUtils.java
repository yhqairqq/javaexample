package com.ako.example.jdk.guavaFileUtils;


import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yanghuanqing@wdai.com on 21/11/2017.
 */
public class GuavaFileUtils {

    public static void main(String[] args) {


        final File file = new File("test.txt");

        ExecutorService executors = Executors.newFixedThreadPool(4);
        for(int i=0;i<4;i++){
            executors.execute(new Runnable() {
                @Override
                public void run() {
                  for(int j=0;j<100;j++){
                      try {
                          String message =j+":" +Thread.currentThread().getName()+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

                          FileUtils.write(file, message+"\r\n","UTF-8",true);

                          System.out.println(Thread.currentThread().getName()+"写入一条");
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
                }
            });

        }
        executors.shutdown();








    }
}
