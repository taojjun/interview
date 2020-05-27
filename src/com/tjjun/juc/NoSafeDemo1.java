package com.tjjun.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 错误现象
 * java.util.ConcurrentModificationException
 *
 */
public class NoSafeDemo1 {
    public static void main(String[] args) {
        mapNotSafe();

    }

    /**
     * 解决方法
     * new ConcurrentHashMap<>();
     */
    private static void mapNotSafe() {
        //        Map<Object, Object> map = new HashMap<>();
        Map<Object, Object> map = new ConcurrentHashMap<>();
        for (int i=0;i<=10;i++) {
            new Thread(() ->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    /**
     * 解决方法
     * 1.new CopyOnWriteArraySet();
     */
    private static void setNotSafe() {
//        Set set = new HashSet();
        Set set = new CopyOnWriteArraySet();
        for (int i=0;i<=10;i++) {
            new Thread(() ->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
    /**
     * 解决方法
     * 1.Collections.synchronizedList(new ArrayList());
     * 2.new Vector();
     * 3.new CopyOnWriteArrayList();---写时复制集合
     */
    private static void listNotSafe() {
        List list = new CopyOnWriteArrayList();
//        List list = new Vector();
//        List list = new ArrayList();
//        List list = Collections.synchronizedList(new ArrayList());
        for (int i=0;i<=10;i++) {
            new Thread(() ->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
