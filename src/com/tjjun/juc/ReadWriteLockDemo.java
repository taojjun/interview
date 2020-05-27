package com.tjjun.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1818:50
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i=1;i<=5;i++){
            final int temp = i;
            new Thread(()->{
                myCache.put(String.valueOf(temp),temp);
            },String.valueOf(i)).start();
        }
        for (int i=1;i<=5;i++){
            final int temp = i;
            new Thread(()->{
                myCache.get(String.valueOf(temp));
            },String.valueOf(i)).start();
        }

    }
}
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            map.put(key,value);
            System.out.println("写入完成--"+key);
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            Object result = map.get(key);
            System.out.println("读取完成--"+result);
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
