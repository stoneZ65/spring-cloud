package com.demo.gateway.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @description: 创建螳螂类，监听着蝉的类的动作
 * @author: zhanglei
 * @date: 2021-07-01 15:53
 **/
public class BeanFactory {

    public static BaseService newInstanc(Class classFile) {
        // 1. 创建蝉，真实类对象
        BaseService trueCicada = new Cicada();
        // 2.创建代理类 螳螂
        InvocationHandler prayingMantis = new PrayingMantis(trueCicada);
        //  3.向Jvm索要代理对象 其实就是监听的对象，
        Class[] classArray = {BaseService.class};
        BaseService baseService = (BaseService) Proxy.newProxyInstance(classFile.getClassLoader(), classArray, prayingMantis);
        return baseService;
    }

    // 测试Demo
    public static void main(String[] args) {
        BaseService baseService = newInstanc(Cicada.class);
        baseService.mainService();
        // 测试结果 ：主要业务
        //           螳螂捕蝉 - 次要业务

        //代理的本质:其实就是一种行为的监听，对代理对象（$proxy InvocationHandler）的一种监听行为

        //代理模式组成:
        //  接口：声明需要被监听行为
        //  代理实现类（InvocationHandler）:次要业务，次要业务和主要业务绑定执行
        //  代理对象（监听对象）
    }
}
