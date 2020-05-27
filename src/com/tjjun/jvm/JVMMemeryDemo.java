package com.tjjun.jvm;

import java.util.Random;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.jvm
 * @Description:造成内存溢出异常OOM
 * Java.lang.OutOfMemoryError: Java heap space
 * @date 2020/5/1921:10
 */
public class JVMMemeryDemo {
    public static void main(String[] args) {
        String str = "taojjun";
        while (true){
            str += str +new Random().nextInt(88888888)+new Random().nextInt(999999999);
        }
    }
}
