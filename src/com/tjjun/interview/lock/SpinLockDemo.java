package com.tjjun.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.lock
 * @Description:
 * @date 2020/5/2711:06
 */
public class SpinLockDemo {
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();
    public void myLock(){
        System.out.println(Thread.currentThread().getName()+"\t invoked lock O(∩_∩)O");
        Thread thread = Thread.currentThread();
        while (!threadAtomicReference.compareAndSet(null,thread)){
        }

    }
    public void myUnLock(){
        System.out.println(Thread.currentThread().getName()+"\t invoked unlock ");
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread,null);
    }
    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"t1").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"t2").start();
    }
}
