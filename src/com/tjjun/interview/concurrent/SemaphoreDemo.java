package com.tjjun.interview.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author taojjun
 * @Title:信号量
 * 1.共享资源互斥
 * 2.线程控制
 * @Package com.tjjun.interview.concurrent
 * @Description:类似抢车位;控制线程数量
 * 1进入停车场
 * 1离开停车场
 * 2进入停车场
 * 2离开停车场
 * 5进入停车场
 * 5离开停车场
 * 6进入停车场
 * 6离开停车场
 * 3进入停车场
 * 3离开停车场
 * 4进入停车场
 * 4离开停车场
 * @date 2020/5/2714:55
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(2);
                    System.out.println(Thread.currentThread().getName()+"进入停车场");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"离开停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                }


            },String.valueOf(i)).start();
        }
    }
}
