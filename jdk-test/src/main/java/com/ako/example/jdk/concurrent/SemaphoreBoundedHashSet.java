package com.ako.example.jdk.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by yanghuanqing@wdai.com on 26/07/2017.
 */
public class SemaphoreBoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;
    public SemaphoreBoundedHashSet(int bound){
        this.set = (Set<T>) Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(bound);
    }
    public boolean add(T o) throws InterruptedException{
        sem.acquire();
        boolean wasAdded = false;
        try{
            wasAdded = set.add(o);
            return wasAdded;

        }finally {
            if(!wasAdded){
                sem.release();
            }
        }
    }
    public boolean remove(Object o){
        boolean wasRemoved = set.remove(o);
        if(wasRemoved){
            sem.release();
        }
        return wasRemoved;
    }
}
