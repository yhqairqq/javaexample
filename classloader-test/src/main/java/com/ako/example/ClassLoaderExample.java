package com.ako.example;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/3.
 */
public class ClassLoaderExample {


    public static void main(String args[]) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String filePath = "/Users/YHQ/workspace/javaexample/jar-test/src/main/java/com/ako/example/test.jar";
        DiskClassLoader diskClassLoader = new DiskClassLoader(filePath);
        Class<?> clazz = diskClassLoader.loadClass("com.ako.example.Student");
        Object obj = clazz.newInstance();
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        setName.invoke(obj, "杨焕清");
        Method getName = clazz.getDeclaredMethod("getName");
        String name = (String) getName.invoke(obj);
        diskClassLoader = null;
        obj = null;
        System.gc();

        System.out.println(name);
    }

    @Test
    public void test2() {
        int i = 10;
        System.out.println(i >> 1);
    }

    @Test
    public void test3() throws IOException {
        String jarFileName = "/Users/YHQ/workspace/javaexample/jar-test/src/main/test.jar";
        ZipFile
                zipfile = new ZipFile(jarFileName);
        Enumeration<?> zipenum = zipfile.entries();
        ZipEntry entry = null;
        String tempClassName = null;

        while (zipenum.hasMoreElements()) {
            entry = (ZipEntry) zipenum.nextElement();
            tempClassName = entry.getName();
            System.out.println(tempClassName);
        }
    }

    @Test
    public void test4() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, URISyntaxException {
        File file = new File("/Users/YHQ/workspace/javaexample/jar-test/target/lib/jar-test-1.0-SNAPSHOT.jar");
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> classLoaderClass = URLClassLoader.class;
        Method addUrl = classLoaderClass.getDeclaredMethod("addURL",new Class[]{URL.class});
        addUrl.setAccessible(true);
        addUrl.invoke(urlClassLoader, file.toURI().toURL());
        String className = "com.ako.example.Student";
        Class studentClass = urlClassLoader.loadClass(className);
        Object obj = studentClass.newInstance();
        Method setName = studentClass.getDeclaredMethod("setName", String.class);
        Method getName = studentClass.getDeclaredMethod("getName");
        setName.invoke(obj, "杨焕清");
        Object name = getName.invoke(obj);
        System.out.println(name);


    }
}
