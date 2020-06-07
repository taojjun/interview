package com.tjjun.interview.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ConcurrentModificationException;
import java.util.Random;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.jvm
 * @Description:四大引用
 * @date 2020/5/2916:34
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("=================================");
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

    }
}
