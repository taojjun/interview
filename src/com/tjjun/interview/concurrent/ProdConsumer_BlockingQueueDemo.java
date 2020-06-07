package com.tjjun.interview.concurrent;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.concurrent
 * @Description:
 * @date 2020/5/2718:09
 */
public class ProdConsumer_BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        MyResource1 myResource = new MyResource1(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            try {
                System.out.println("生产者线程已启动");
                myResource.myProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者\t").start();
        new Thread(()->{
            try {
                System.out.println("消费者线程已启动");
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者\t").start();
        TimeUnit.SECONDS.sleep(3);
        myResource.stop();
        System.out.println(Thread.currentThread().getName()+"\t叫停生产！！！");
    }
}

class MyResource{
    //全局运行标志，默认自动执行生产消费，使用volatile保证变量在多线程中的可见性
    private volatile boolean FLAG = true;
    //原子整型，保证原子性
    private AtomicInteger cookieCount = new AtomicInteger();
    //默认为null，通过构造方法注入实现其中实现类的适配
    private BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    /**
        * @Description: 生产方法
        * @author taojjun
        * @date 2020/5/27 18:14
    */
    public void myProduct() throws InterruptedException {
        String count = null;
        boolean result;
        while (FLAG){
            count = cookieCount.incrementAndGet() + "";
            result = blockingQueue.offer(count,2L, TimeUnit.SECONDS);
            if (result){
                System.out.println(Thread.currentThread().getName()+"\t已生产第"+count+"块蛋糕");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t生产蛋糕失败");
            }
            TimeUnit.SECONDS.sleep(1);


        }
        System.out.println(Thread.currentThread().getName()+"\t叫停生产消费活动！停止生产！");
    }
    /**
        * @Description: 消费方法
        * @author taojjun
        * @date 2020/5/27 18:30
    */
    public void myConsumer() throws InterruptedException {
        String result;
        while (FLAG){
            result = blockingQueue.poll(4L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t无法取到蛋糕，停止消费！");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t已消费第"+result+"块蛋糕");
        }
    }
    public void stop(){
        this.FLAG = false;
    }
}
class MyResource1{
    private volatile boolean FLAG = true;
    private AtomicInteger cookieCount = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource1(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProduct() throws InterruptedException {
        String data = null;
        boolean result;
        while (FLAG){
            data = cookieCount.incrementAndGet()+"";
            result = blockingQueue.offer(data,2L,TimeUnit.SECONDS);
            if (result){
                System.out.println(Thread.currentThread().getName()+"\t生产第"+data+"块蛋糕成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t生产第"+data+"块蛋糕失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t老板叫停活动！停止生产蛋糕");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(3L,TimeUnit.SECONDS);
            if (null == result || "".equalsIgnoreCase(result)){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t获取蛋糕失败！停止消费蛋糕");
                return;
            }else {
                System.out.println(Thread.currentThread().getName()+"\t消费第"+result+"块蛋糕成功");
            }
        }
    }
    public void stop(){
        FLAG = false;
    }
}