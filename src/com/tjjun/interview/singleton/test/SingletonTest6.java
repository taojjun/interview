package com.tjjun.interview.singleton.test;

import com.tjjun.interview.singleton.Singleton6;

import java.util.concurrent.*;

public class SingletonTest6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton6> callable = new Callable<Singleton6>() {
            @Override
            public Singleton6 call() throws Exception {
                return Singleton6.getINSTANCE();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton6> future1 = es.submit(callable);
        Future<Singleton6> future2 = es.submit(callable);
        Singleton6 s1 = future1.get();
        Singleton6 s2 = future2.get();

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
