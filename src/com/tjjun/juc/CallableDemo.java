package com.tjjun.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1816:01
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }
}



class  MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in method");
        return 123;
    }
}
