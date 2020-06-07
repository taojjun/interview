package com.tjjun.interview.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.concurrent
 * @Description:实现多线程的第三种方式 ，实现Callable接口
 * 作用：可以设置多个线程执行不同的工作，最后汇总，分支合并的思想；例如多个线程计算一个任务，最后把各个结果加在一起
 * 当调用get()方法是会造成其他线程阻塞，所有最后把这一步放在最后
 * @date 2020/5/2813:35
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask).start();
        //通过get()方法获得call()方法中的结果
        System.out.println(futureTask.get());

    }
}

class MyThread implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t执行线程");
        return 123;
    }
}
