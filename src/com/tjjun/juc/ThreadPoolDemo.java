package com.tjjun.juc;

import java.util.concurrent.*;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1910:00
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());//系统CPU内核数

        /**
            项目场景：CPU密集型;I/O密集型
            CPU密集型：maximumPoolSize = CPU内核数+1或者+2
        */
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                14,
                4L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()//默认拒绝策略，直接抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy()//将任务回退给调用者
//                new ThreadPoolExecutor.DiscardPolicy()//直接抛弃任务
                new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃等待时间最久的任务
        );
        try {
            for (int i=1;i<=20;i++){
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                try {
                    threadPool.execute(()->{

                        System.out.println(Thread.currentThread().getName()+"\t执行中");
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void initPool() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//固定数量的线程
//       ExecutorService threadPool = Executors.newSingleThreadExecutor();//只有一个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//动态增加线程的线程池

        try {
            for (int i=1;i<=10;i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.execute(()->{

                    System.out.println(Thread.currentThread().getName()+"\t执行中");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
