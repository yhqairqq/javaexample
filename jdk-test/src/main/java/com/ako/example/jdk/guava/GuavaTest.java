package com.ako.example.jdk.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.cache.*;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yanghuanqing@wdai.com on 19/09/2017.
 */
public class GuavaTest {


    LoadingCache<String, ReentrantLock>
            cache =

            //
            CacheBuilder
                    .newBuilder()
                    //给定时间内没有被读写，则回收
                    .expireAfterWrite(10, TimeUnit.MINUTES)
                    .concurrencyLevel(8) // 设置并发级别为8
                    //设置缓存容量的初始容量为10
                    .initialCapacity(10)
                    //设置缓存最大容量为100，超过100后会按照LRU最近最少使用算法来移除缓存项
                    .maximumSize(20)
                    .recordStats()
                    .removalListener(new RemovalListener<String, ReentrantLock>() {
                        @Override
                        public void onRemoval(RemovalNotification<String, ReentrantLock> notification) {
                            System.out.println(
                                    notification.getKey() + " was removed,cause in " + notification.getCause()
                            );
                        }
                    })
                    //build方法中可以指定CacheLoader ，在缓存不存在时候通过CacheLoader的实现自动加载缓存
                    //当本地没有命中时候，调用load方法获取结果并将结果缓存
                    .build(
                            new CacheLoader<String, ReentrantLock>() {
                                @Override
                                public ReentrantLock load(String s) throws Exception {
                                    return new ReentrantLock();
                                }
                            }
                    );

    @Test
    public void test() throws ExecutionException {
        for (int i = 0; i < 1000 * 1000; i++) {
            cache.get(String.valueOf(new Random().nextInt(20)));
        }

//        Preconditions.checkArgument(0>1,"前者必须大于后者");


        Object o1 = new Object();

        Object o2 = new Object();

        System.out.println(Objects.equal(o1, o2));

        System.out.println(o2 == o1);


        Student s1 = new Student("s2", 21);
        Student s2 = new Student("s1", 22);
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        System.out.println(s1);


        System.out.println("cache status:\n" + cache.stats().toString());
    }

}

@Data
@ToString
class Student {
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @NotNull
    private String name;
    private int age;

    public String toString() {
        return MoreObjects.toStringHelper(this).add("x", "1").toString();
    }

}