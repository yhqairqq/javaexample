package com.wd.bigdata.javaexample.proxy.volatileandstatic;

/**
 * Created by Peter on 17/05/2017.
 */
public class VolatileAndStaticTest {




    public static void main(String args[]){


        new TestThread("A").run();
        new TestThread("B").run();
        new TestThread("C").run();
        new TestThread("D").run();
        new TestThread("E").run();
        new TestThread("F").run();
        new TestThread("G").run();




    }

}
