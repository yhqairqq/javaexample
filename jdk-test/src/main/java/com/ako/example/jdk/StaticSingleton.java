package com.ako.example.jdk;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/15.
 *
 *由于加载一个类时，其内部类不会被加载，这样保证了只能调用getInstance()时才会产生实例
 * 控制了生成实例的时间，实现了延迟加载
 * 并且去掉了synchronized让性能更优，用static来确保唯一性
 *
 *
 */
public class StaticSingleton {
    private StaticSingleton() {

    }
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
