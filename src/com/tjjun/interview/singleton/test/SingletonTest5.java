package com.tjjun.interview.singleton.test;

import com.tjjun.interview.singleton.Singleton5;

import java.util.concurrent.*;

public class SingletonTest5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton5> callable = new Callable<Singleton5>() {
            @Override
            public Singleton5 call() throws Exception {
                return Singleton5.getINSTANCE();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton5> future1 = es.submit(callable);
        Future<Singleton5> future2 = es.submit(callable);
        Singleton5 s1 = future1.get();
        Singleton5 s2 = future2.get();

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);


        /**
         * true
         * com.tjjun.interview.singleton.Singleton5@7f31245a
         * com.tjjun.interview.singleton.Singleton5@7f31245a
         */

    }
}
