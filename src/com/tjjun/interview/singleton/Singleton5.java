package com.tjjun.interview.singleton;

/**
 * 懒汉式
 * 适用于多线程，不存在线程安全问题，使用同步
 * 仅使用DCL机制并不能限制指令重排，还是会存在线程安全问题，所以需要使用volatile，实现禁止指令重排
 */
public class Singleton5 {
    //使用volatile，实现禁止指令重排
    private static volatile Singleton5 INSTANCE;
    public static Singleton5 getINSTANCE() throws InterruptedException {
        if (INSTANCE == null){
            synchronized (Singleton5.class) {
                if (INSTANCE == null){
                    Thread.sleep(1000);
                    INSTANCE = new Singleton5();
                }
            }
        }
        return  INSTANCE;
    }
    private Singleton5(){

    }

}
