package com.tjjun.interview.classloader;

public class Son extends Father{
    private int i = test();

    private static int j = method();

    static {
        System.out.print("---6---");
    }
    Son(){
        System.out.print("---7---");
    }
    {
        System.out.print("---8---");
    }
    public int test() {
        System.out.print("---9---");
        return 0;
    }

    private static int method() {
        System.out.print("---10---");
        return 0;
    }
}
