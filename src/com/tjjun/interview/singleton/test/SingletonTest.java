package com.tjjun.interview.singleton.test;

import com.tjjun.interview.singleton.Singleton4;

import java.util.concurrent.*;

public class SingletonTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton4> callable = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getINSTANCE();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> future1 = es.submit(callable);
        Future<Singleton4> future2 = es.submit(callable);
        Singleton4 s1 = future1.get();
        Singleton4 s2 = future2.get();

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);


        /**
         * false
         * com.tjjun.interview.singleton.Singleton4@7f31245a
         * com.tjjun.interview.singleton.Singleton4@6d6f6e28
         */

    }
}
