package com.tjjun.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1817:39
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=1;i<=6;i++) {
            new Thread( () -> {
                System.out.println(Thread.currentThread().getName()+"已经走了");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"关门啦");
    }
}
