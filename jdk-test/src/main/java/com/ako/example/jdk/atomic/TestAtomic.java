package com.ako.example.jdk.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yanghuanqing@wdai.com on 23/06/2017.
 */
public class TestAtomic {

    /**
     * @param java中的原子操作类AtomicInteger
     * @author yangcq
     *
     * 关于AtomicInteger的说明（来自官方文档注解）
     * /**
     * An {@code int} value that may be updated atomically.  See the
     * {@link java.util.concurrent.atomic} package specification for
     * description of the properties of atomic variables. An
     * {@code AtomicInteger} is used in applications such as atomically
     * incremented counters, and cannot be used as a replacement for an
     * {@link Integer}. However, this class does extend
     * {@code Number} to allow uniform access by tools and utilities that
     * deal with numerically-based classes.
     *
     * @since 1.5
     * @author Doug Lea
     */
    public static void main(String[] args) {
        // 初始值为1
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println("--初始值atomicInteger = " + atomicInteger);

        // 以原子方式将当前值加1，注意这里返回的是自增前的值
        System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.getAndIncrement());
        System.out.println("--自增后的 atomicInteger = " + atomicInteger);

        // 以原子方式将当前值减1，注意这里返回的是自减前的值
        System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.decrementAndGet());
        System.out.println("--自减后的 atomicInteger = " + atomicInteger);

        // 以原子方式将当前值与括号中的值相加，并返回结果
        System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.addAndGet(10));
        System.out.println("--自减后的 atomicInteger = " + atomicInteger);

        // 如果输入的值等于预期的值，则以原子方式将该值设置成括号中的值
        System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.compareAndSet(1, 2));
        System.out.println("--自减后的 atomicInteger = " + atomicInteger);
        System.out.println("atomicInteger.getAndIncrement() = " + atomicInteger.compareAndSet(11, 9999));
        System.out.println("--自减后的 atomicInteger = " + atomicInteger);

        /**
         * 一，AtomicInteger 是如何实现原子操作的呢？
         *
         * 我们先来看一下getAndIncrement的源代码：
         *    public final int getAndIncrement() {
         *        for (;;) {
         *            int current = get();  // 取得AtomicInteger里存储的数值
         *            int next = current + 1;  // 加1
         *            if (compareAndSet(current, next))   // 调用compareAndSet执行原子更新操作
         *                return current;
         *        }
         *    }
         *
         * 这段代码写的很巧妙：
         * 1，compareAndSet方法首先判断当前值是否等于current；
         * 2，如果当前值 = current ，说明AtomicInteger的值没有被其他线程修改；
         * 3，如果当前值 != current，说明AtomicInteger的值被其他线程修改了，这时会再次进入循环重新比较；
         *
         * 注意这里的compareAndSet方法，源代码如下：
         * public final boolean compareAndSet(int expect, int update) {
         *     return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
         * }
         *
         * 调用Unsafe来实现
         * private static final Unsafe unsafe = Unsafe.getUnsafe();
         *
         * 二，java提供的原子操作可以原子更新的基本类型有以下三个：
         *
         * 1，AtomicBoolean
         * 2，AtomicInteger
         * 3，AtomicLong
         *
         * 三，java提供的原子操作，还可以原子更新以下类型的值：
         *
         * 1，原子更新数组，Atomic包提供了以下几个类：AtomicIntegerArray、AtomicLongArray、AtomicReferenceArray
         * 2，原子更新引用类型，也就是更新实体类的值，比如AtomicReference<User>
         * AtomicReference:原子更新引用类型的值
         * AtomicReferenceFieldUpdater:原子更新引用类型里的字段
         * AtomicMarkableReference:原子更新带有标记位的引用类型
         * 3，原子更新字段值
         * AtomicIntegerFieldUpdater:原子更新整形的字段的更新器
         * AtomicLongFieldUpdater:原子更新长整形的字段的更新器
         * AtomicStampedReference:原子更新带有版本号的引用类型的更新器
         *
         *
         */
    }


}
