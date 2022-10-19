package com.demo.gateway.designPattern.proxy;

/**
 * @description: 代理模式-真实对象
 * @author: zhanglei
 * @date: 2021-07-01 15:03
 **/
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("放学后去打篮球。");
    }
}
