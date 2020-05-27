package com.tjjun.interview.cas;


import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author taojjun
 * @Title:
 * @Package com.tjjun.interview.cas
 * @Description:
 * @date 2020/5/2615:37
 */
public class AtomicReferceDemo {
    public static void main(String[] args) {
        User user1 = new User("taojjun1", 181);
        User user2 = new User("taojjun2", 182);
        User user3 = new User("taojjun3", 183);
        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(user1);
        System.out.println(reference.compareAndSet(user1, user2)+"\t"+reference.get().toString());
        System.out.println(reference.compareAndSet(user2, user3)+"\t"+reference.get().toString());
    }
}

class User{
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}