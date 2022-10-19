package com.demo.gateway.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
public class Cardinal implements InvocationHandler {

    //监听代理对象
    private Object proxyOne;

    public Cardinal(Object proxyOne) {
        this.proxyOne = proxyOne;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(proxyOne, args);
        secondaryMain();
        return null;
    }

    private void secondaryMain() {
        System.out.println("黄雀吃螳螂 - 次要业务");
    }
}
