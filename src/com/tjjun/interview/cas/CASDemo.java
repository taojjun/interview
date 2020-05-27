package com.tjjun.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.cas
 * @Description:
 * @date 2020/5/2613:35
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 100)+"\tcurrent data\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1000)+"\tcurrent data\t"+atomicInteger.get());
    }
}
