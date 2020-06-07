package com.tjjun.interview.concurrent;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.concurrent
 * @Description:不存储数据的阻塞队列，生产一个，消费一个
 * @date 2020/5/2716:18
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> synQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                synQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                synQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                synQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\tget"+synQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\tget"+synQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\tget"+synQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();

    }
}
