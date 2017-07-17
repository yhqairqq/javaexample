package com.wd.bigdata.javaexample.proxy.serializable;

import java.io.*;

/**
 * Created by yanghuanqing@wdai.com on 20/06/2017.
 */
public class SerializableTest2 implements Serializable{
    private static final long serialVersionUID = 1692785964819918083L;

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("serialize");

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(new SerializableTest());
        oos.flush();
        oos.close();
    }
}
