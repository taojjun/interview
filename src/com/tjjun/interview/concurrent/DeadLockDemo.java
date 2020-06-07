package com.tjjun.interview.concurrent;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.concurrent
 * @Description:死锁
 * 两个或两个以上线程，在执行过程中因争夺资源而造成的互相等待现象
 *
 * jps -l 查看线程号
 * jstack 线程号       查看栈信息
 * 结果：
 * Java stack information for the threads listed above:
 * ===================================================
 * "lockB":
 *         at com.tjjun.interview.concurrent.HoldedLock.run(DeadLockDemo.java:36)
 *         - waiting to lock <0x000000076bae3fc8> (a java.lang.String)
 *         - locked <0x000000076bae4000> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 * "lockA":
 *         at com.tjjun.interview.concurrent.HoldedLock.run(DeadLockDemo.java:36)
 *         - waiting to lock <0x000000076bae4000> (a java.lang.String)
 *         - locked <0x000000076bae3fc8> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * Found 1 deadlock.
 * @date 2020/5/2815:41
 */
public class DeadLockDemo {


    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldedLock(lockA,lockB),lockA).start();
        new Thread(new HoldedLock(lockB,lockA),lockB).start();
    }
}
class HoldedLock implements Runnable{
    private String lockA;
    private String lockB;

    public HoldedLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"持有"+lockA+"想要获取"+lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"持有"+lockB+"想要获取"+lockA);
            }
        }
    }
}