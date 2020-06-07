package com.tjjun.interview.concurrent;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.concurrent
 * @Description:类似做减法，为0时唤醒某个线程
 * @date 2020/5/2714:12
 */
public class CountDownLatchDemo {
    static CountDownLatch downLatch = new CountDownLatch(6);
    public static void main(String[] args) throws InterruptedException {
        for (int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国被灭啦");
                downLatch.countDown();
            },CountryEnum.for_Each(i).getRtnMessage()).start();
        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t秦国统一中国啦");
    }

    private static void closeDoor() throws InterruptedException {
        for (int i=1;i<=2;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t走啦");
                downLatch.countDown();
            },String.valueOf(i)).start();
        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t关门啦");
    }
}
/**
    * @Description: 使用枚举类，实现代码中的判断不同国家的功能，将枚举类当成数据版的MySQL
 * 齐	国被灭啦
 * 燕	国被灭啦
 * 韩	国被灭啦
 * 楚	国被灭啦
 * 赵	国被灭啦
 * 魏	国被灭啦
 * main	秦国统一中国啦
    * @author taojjun
    * @date 2020/5/27 14:40
*/
enum CountryEnum{
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"韩"),
    FIVE(5,"赵"),
    SIX(6,"魏");

    CountryEnum(Integer rtnCode, String rtnMessage) {
        this.rtnCode = rtnCode;
        this.rtnMessage = rtnMessage;
    }
    public static CountryEnum for_Each(int index){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum element : countryEnums) {
            if (index == element.getRtnCode()){
                return element;
            }
        }
        return null;
    }
    private Integer rtnCode;
    private String rtnMessage;

    public Integer getRtnCode() {
        return rtnCode;
    }

    public String getRtnMessage() {
        return rtnMessage;
    }
}