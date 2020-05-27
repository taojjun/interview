package com.tjjun.interview.singleton;

public class SingletonMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("你好");
        /**
         * 饿汉式
         */
        //直接实例化
        Singleton1 singleton1 = Singleton1.INSTANCE;
        Singleton1 singleton2 = Singleton1.INSTANCE;
        System.out.println(singleton1 == singleton2);

        //枚举类型
        Singleton2 singleton21 = Singleton2.INSTACE;
        Singleton2 singleton22 = Singleton2.INSTACE;
        System.out.println(singleton21 == singleton22);
        //静态代码块
        /*Singleton3 singleton3 = Singleton3.INSTACE;
        System.out.println(singleton3);*/


    }
}
