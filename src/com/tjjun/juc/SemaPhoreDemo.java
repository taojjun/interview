package com.tjjun.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1818:10
 */
public class SemaPhoreDemo {
    public static void main(String[] args) {
        Semaphore semaPhore = new Semaphore(3);
        for (int i =1;i<=6;i++){
            new Thread(()->{
                try {
                    semaPhore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t已经抢占了车位");

                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaPhore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
