package com.tjjun.interview.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.cas
 * @Description:解决ABA问题
 * @date 2020/5/279:42
 */
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        new Thread(()->{
            int stap = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本"+stap);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第三次版本"+atomicStampedReference.getStamp());
        },"t1").start();
        new Thread(()->{
            int stap = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本"+stap);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"修改成功否\t"+atomicStampedReference.compareAndSet(100, 200, stap, stap++)+"\t第二次版本"+atomicStampedReference.getStamp());
            System.out.println("当前实际最新值\t"+atomicStampedReference.getReference());
        },"t2").start();
    }
}
