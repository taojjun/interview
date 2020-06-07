package com.tjjun.interview.jvm;

import java.util.Random;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.jvm
 * @Description:
 * @date 2020/5/2917:02
 */
public class OOMDemo {
    public static void main(String[] args) {
       byte[] b = new byte[100*1024*1024];
    }
}
