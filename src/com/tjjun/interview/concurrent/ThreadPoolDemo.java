package com.tjjun.interview.concurrent;

import java.util.concurrent.*;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.concurrent
 * @Description:线程池
 * @date 2020/5/2814:23
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                4,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
