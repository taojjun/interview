package com.tjjun.interview.fibonacci_sequence;

public class TestStep2 {
    /**
     * 循环迭代实现斐波那契数列
     *
     */
    public int f(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int one = 2;//到达f(n-1),还剩两级台阶，两种走法
        int two = 1;//到达f(n-2)，还剩一级台阶，一种走法
        int sum = 0;
        for (int i=3;i<=n;i++){
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;
    }
    public static void main(String[] args) {
        TestStep2 testStep = new TestStep2();
        for (int i = 1; i < 101; i++) {
            System.out.println("n=" + i + "--result=" + testStep.f(i));
        }
    }
}
