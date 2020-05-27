package com.tjjun.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1822:00
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        //抛出异常
//        System.out.println(arrayBlockingQueue.add("a"));
//        System.out.println(arrayBlockingQueue.add("b"));
        //System.out.println(arrayBlockingQueue.add("c"));
        //Exception in thread "main" java.lang.IllegalStateException: Queue full
//        arrayBlockingQueue.remove();
//        arrayBlockingQueue.remove();
//        arrayBlockingQueue.remove();
        //Exception in thread "main" java.util.NoSuchElementException
        //特殊值
//        System.out.println(arrayBlockingQueue.offer("a"));
//        System.out.println(arrayBlockingQueue.offer("b"));
//        System.out.println(arrayBlockingQueue.offer("c"));
        /**
         true
         true
         false
        */
//        System.out.println(arrayBlockingQueue.poll());
//        System.out.println(arrayBlockingQueue.poll());
//        System.out.println(arrayBlockingQueue.poll());
        /**
         a
         b
         null
        */

        //阻塞
//        arrayBlockingQueue.put("a");
//        arrayBlockingQueue.put("b");
//        arrayBlockingQueue.put("c");

//        System.out.println(arrayBlockingQueue.take());
//        System.out.println(arrayBlockingQueue.take());
//        System.out.println(arrayBlockingQueue.take());

        //超时退出
        System.out.println(arrayBlockingQueue.offer("a", 3L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("b", 3L, TimeUnit.SECONDS));
//        System.out.println(arrayBlockingQueue.offer("c", 3l, TimeUnit.SECONDS));

        System.out.println(arrayBlockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(3L, TimeUnit.SECONDS));

        /**
         true
         true
         a
         b
         null
        */
    }
}
