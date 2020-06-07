package com.tjjun.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个售票员    卖   30张票
 */
public class SaleTicketDemo1 {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(() ->{for (int i = 0; i < 400; i++)  ticket.sale();},"A").start();
        new Thread(() ->{for (int i = 0; i < 400; i++)  ticket.sale();},"B").start();
        new Thread(() ->{for (int i = 0; i < 400; i++)  ticket.sale();},"C").start();
//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 400; i++){
//                    ticket.sale();
//                }
//            }
//        },"A").start();
//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 400; i++){
//                    ticket.sale();
//                }
//            }
//        },"B").start();
//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 400; i++){
//                    ticket.sale();
//                }
//            }
//        },"C").start();
    }

}

class Ticket{
    int num = 5;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (num > 0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+(num--)+"张票；还剩"+num+"张");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
