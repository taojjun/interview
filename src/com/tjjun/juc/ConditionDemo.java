package com.tjjun.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
    * @Description:
    * @author taojjun
    * @date 2020/5/18 14:21
*/
public class ConditionDemo {
    public static void main(String[] args) {
    ShareData shareData = new ShareData();
    new Thread( () -> {
        for (int i=1;i<=3;i++) {
            shareData.myPrint(2,shareData.getC1(),shareData.getC2(),1);
        }
    },"A").start();
    new Thread( () -> {
        for (int i=1;i<=3;i++) {
            shareData.myPrint(3,shareData.getC2(),shareData.getC3(),2);
        }
    },"B").start();
    new Thread( () -> {
        for (int i=1;i<=3;i++) {
            shareData.myPrint(4,shareData.getC3(),shareData.getC1(),3);
        }
    },"C").start();

    }


}


class ShareData{
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1= lock.newCondition();
    private Condition c2= lock.newCondition();
    private Condition c3= lock.newCondition();

    public Condition getC1() {
        return c1;
    }

    public Condition getC2() {
        return c2;
    }

    public Condition getC3() {
        return c3;
    }

    public void myPrint(int loop , Condition source,Condition target,int tnum){

        lock.lock();
        try {
            while (flag != tnum){
                source.await();
            }

            for (int i =1;i<=loop;i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag = tnum == 1 ? tnum + 1 : tnum == 2 ? tnum + 1 : tnum -2;
            target.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}