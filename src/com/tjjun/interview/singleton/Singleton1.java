package com.tjjun.interview.singleton;

/**
 * 单例设计模式--不存在线程安全问题
 * 1.构造器私有化
 * 2.自行创建，并用静态变量保存
 * 3.向外提供实例
 * 4.强调这是一个单例，使用final修饰
 *
 *
 * 饿汉式:不管是否需要这个类，都会创建，因为有一个静态变量初始化时会创建这个类
 */
public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();
    private Singleton1(){

    }
}
