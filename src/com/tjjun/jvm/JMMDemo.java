package com.tjjun.jvm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.jvm
 * @Description:
 * @date 2020/5/2014:40
 */
public class JMMDemo {
    public static void main(String[] args) {
        atomicByVolatile();
        seeOkByVolatile();
    }

    private static void atomicByVolatile() {
        MyNumber m = new MyNumber();
        for (int i =0;i<20;i++){
            new Thread(()->{
                for (int j=0;j<1000;j++){
                    m.addAtomicInteger();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() >2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t最终结果\t"+m.atomicInteger);
        //main	最终结果	19743
        //main	最终结果	19766
    }

    /**
     * @param  
     * @return void
     * @throws 
     * @author taojjun
     * @description volatile可以保证可见性，及时通知其他线程，主物理内存中的值被修改
     * volatile不保证原子性
     * 原子性：不可分割，完整性，某个线程执行某个具体业务时，中间不可被打断，需要整体完整；要么同时成功，要么同时失败
     * @date 2020/5/20 15:59
    */
    private static void seeOkByVolatile() {
        MyNumber myNumber = new MyNumber();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.add();
            System.out.println(myNumber.a);
        },"AAA").start();

        while (myNumber.a == 0){

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over"+myNumber.a);
    }
}
class MyNumber{
     volatile int a = 0;
    public void add(){
        this.a=100;
    }
    //volatile不保证原子性
    public  void addPlusPlus(){
        this.a++;;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomicInteger(){
        atomicInteger.getAndIncrement();
    }
}