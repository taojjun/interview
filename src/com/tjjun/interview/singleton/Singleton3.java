package com.tjjun.interview.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 使用静态代码块:适合复杂实例化，比如从配置文件中读取bean的属性信息
 */
public class Singleton3 {
    public static Singleton3 INSTACE;
    private String name;
    static {
        try {
            Properties properties = new Properties();
            properties.load(Singleton3.class.getResourceAsStream("singleton.properties"));
            INSTACE = new Singleton3(properties.getProperty("name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Singleton3(){
    }
    private Singleton3(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "name='" + name + '\'' +
                '}';
    }
}
