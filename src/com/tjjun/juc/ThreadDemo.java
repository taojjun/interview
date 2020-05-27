package com.tjjun.juc;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.juc
 * @Description:
 * @date 2020/5/1816:06
 */
public class ThreadDemo {
    public static void main(String[] args) {
        new Thread(new MyThread1("继承Thread类的线程")).start();
    }
}
class MyThread1 extends Thread{
    private String name;
    MyThread1(){
    }
    MyThread1(String name){
        this.name = name;
    }
    @Override
    public void run() {
        Thread.currentThread().setName(name);
        System.out.println("mythread name is -"+name);
    }
}
