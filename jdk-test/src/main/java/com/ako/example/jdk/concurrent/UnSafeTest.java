package com.ako.example.jdk.concurrent;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by yanghuanqing@wdai.com on 2018/6/27.
 */
public class UnSafeTest {
    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        Student2 student = new Student2("张三",20);
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe)f.get(null);
        Field field = Student2.class.getDeclaredField("name");
        unsafe.putObject(student,unsafe.objectFieldOffset(field),"王五");
        System.out.println(student.getName());
    }
}

class Student2{
    String name;
    int age;

    public Student2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
