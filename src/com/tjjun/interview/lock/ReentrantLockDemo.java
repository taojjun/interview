package com.tjjun.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.lock
 * @Description:可重入锁（递归锁）
 *                  同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码
 *                  线程可以进入任何一个它已经拥有的锁所同步的代码块
 *      1.synchronized
 *              Thread-0	invoked sendSNS()
 *              Thread-0	invoked sendEmail()
 *              Thread-1	invoked sendSNS()
 *              Thread-1	invoked sendEmail()
 *       2.ReentrantLock
 *              t3	invoked get()
 *              t3	invoked set()
 *              t4	invoked get()
 *              t4	invoked set()
 * @date 2020/5/279:12
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(()->{phone.sendSMS();}).start();
        new Thread(()->{phone.sendSMS();}).start();

        TimeUnit.SECONDS.sleep(2);

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();
    }
}
class Phone implements Runnable{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\tinvoked sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\tinvoked sendEmail()");
    }
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    private void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\tinvoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    private void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\tinvoked set()");
        }finally {
            lock.unlock();
        }
    }
}
