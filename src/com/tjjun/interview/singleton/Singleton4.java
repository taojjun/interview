package com.tjjun.interview.singleton;

/**
 * 懒汉式
 * 适用于单线程，存在线程安全问题
 */
public class Singleton4 {
    private static Singleton4 INSTANCE;
    public static Singleton4 getINSTANCE() throws InterruptedException {
        if (INSTANCE == null){
            Thread.sleep(1000);
            INSTANCE = new Singleton4();
        }
        return  INSTANCE;
    }
    private Singleton4(){

    }

}
