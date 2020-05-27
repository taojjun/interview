package com.tjjun.juc;

/*class Aircondition{
    private int num = 0;
    public synchronized void increment() throws InterruptedException {
        //判断
//        if (num != 0)
        while (num != 0)
            this.wait();
        //干活
        num++;
        System.out.println(Thread.currentThread().getName()+"----"+num);
        //唤醒其他线程
        this.notifyAll();

    }


    public synchronized void decrement() throws InterruptedException {
        //判断
//        if (num == 0)
        while (num == 0)
            this.wait();
        //干活
        num--;
        System.out.println(Thread.currentThread().getName()+"----"+num);
        //唤醒其他线程
        this.notifyAll();

    }
}*/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
    * @Description:
    * @author taojjun
    * @date 2020/5/18 14:21
*/
class Aircondition {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void increment() throws InterruptedException {

        lock.lock();
        try {
            //判断
            while (num != 0) {
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName() + "----" + num);
            //唤醒其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }


    }



    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num == 0) {

                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName() + "----" + num);
            //唤醒其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }


    }
}

/**
 * 1.高内聚低耦合的前提下:线程 操作 资源类
 * 2.判断、干活、通知
 * 3.防止虚假唤醒--所以在判断条件上必须使用while，java官方文档中的规定，用来防止虚假唤醒
 */
public class ProdConsumerDemo {

    public static void main(String[] args) {
        Aircondition aircondition = new Aircondition();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}
