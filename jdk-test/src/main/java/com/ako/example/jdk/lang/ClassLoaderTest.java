package com.ako.example.jdk.lang;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yanghuanqing@wdai.com on 21/06/2017.
 */
public class ClassLoaderTest extends ClassLoader {

    private String classPath;

    String classname = "com.wd.bigdata.javaexample.proxy.concurrent.ClassA";


    public ClassLoaderTest(String classPath) {
        super();
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(classname, classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + className;

        try {
            InputStream is = new FileInputStream(path);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);

            }
            return stream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String path = "/Users/YHQ/workspace/javaexample/target/classes/com/wd/bigdata/javaexample/proxy/concurrent/";
        ClassLoaderTest classLoader = new ClassLoaderTest(path);
        Class r = classLoader.findClass("ClassA.class");
        System.out.println(classLoader);
        ClassLoaderTest classLoader2 = new ClassLoaderTest(path);
        Class r2 = classLoader.findClass("ClassA.class");
//        System.out.println(classLoader2);
    }
}
