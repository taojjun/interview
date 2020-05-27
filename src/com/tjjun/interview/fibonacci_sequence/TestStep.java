package com.tjjun.interview.fibonacci_sequence;

public class TestStep {
    /**
     * 递归实现斐波那契数列
     * f(n)=f(n-1)+f(n-2)
     */
    public int f(int n){
        if (n == 1 || n ==2){
            return n;
        }
        return  f(n-1)+f(n-2);
    }
    public static void main(String[] args) {
        TestStep testStep = new TestStep();
        for (int i =1;i<101;i++){
            System.out.println("n="+i+"--result="+testStep.f(i));
        }
    }
}
