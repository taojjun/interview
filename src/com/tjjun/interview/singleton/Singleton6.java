package com.tjjun.interview.singleton;

/**
 * 懒汉式
 * 使用静态内部类的方式创建,不会随外部类的加载和初始化而初始化，需要单独加载和初始化
 * 因为是在内部类加载和初始化时创建的，所以是线程安全的
 */
public class Singleton6 {
    private static class Inner {
        private static Singleton6 INSTANCE = new Singleton6();
    }
    public static Singleton6 getINSTANCE() throws InterruptedException {
        Thread.sleep(100);
        return Inner.INSTANCE;
    }
    private Singleton6(){
    }

}
