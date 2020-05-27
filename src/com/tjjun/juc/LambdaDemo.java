package com.tjjun.juc;
//函数式接口注解
@FunctionalInterface
interface Foo{
    public  int add(int x,int y);
    default int mul(int x,int y){
        return x*y;
    }
    default int mul1(int x,int y){
        return x*y+1;
    }
    static int div(int x,int y){
        return x/y;
    }
    static int div1(int x,int y){
        return x/y-1;
    }
}

public class LambdaDemo {
    public static void main(String[] args) {
        Foo foo = (int x,int y) -> {return x+y;};
        System.out.println(foo.add(1,2));
        System.out.println(Foo.div(10,2));
        System.out.println(Foo.div1(10,2));
        System.out.println(foo.mul(10,2));
        System.out.println( foo.mul1(10,2));





    }
}
