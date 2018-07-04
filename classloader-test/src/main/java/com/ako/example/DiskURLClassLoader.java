package com.ako.example;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/4.
 */
public class DiskURLClassLoader extends URLClassLoader {
    public DiskURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public DiskURLClassLoader(URL[] urls) {
        super(urls);
    }

    public DiskURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public void addUrl(URL url){
        super.addURL(url);
    }
}
