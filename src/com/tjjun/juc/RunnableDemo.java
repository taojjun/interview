package com.tjjun.juc;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1816:11
 */
class RunableDemo {
    public static void main(String[] args) {
    new Thread(new MyThread2()).start();
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("我是实现runnable接口的线程");
    }
}