package com.demo.gateway.designPattern.strategy;


/**
 * @description: 饿汉模式 之所以叫饿汉式大家可以理解为他饿，他想提前把对象new出来，这样别人哪怕是第一次获取这个类对象的时候直接就存在这个类了，省去了创建类这一步的开销。
 * @author: zhanglei
 * @date:
 **/
public class Singleton {

    private static Singleton instance = new Singleton();

    /**
     * 私有构造方法，防止被实例化
     */

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}

class SingletonTwo {

    private static volatile SingletonTwo instance = null;

    private SingletonTwo() {
    }

    public static SingletonTwo getInstance() {
        if (instance == null) {
            synchronized (SingletonTwo.class) {
                instance = new SingletonTwo();
            }
        }
        return instance;
    }
}

