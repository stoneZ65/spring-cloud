package com.demo.gateway.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
public class MultiBeanFactory {

    public static BaseService newInstanc(Class classFile) {

        // 1. 创建蝉，真实类对象
        BaseService trueCicada = new Cicada();


        // 2.创建代理类 螳螂
        InvocationHandler prayingMantis = new PrayingMantis(trueCicada);

        // 3.向Jvm索要代理对象
        Class[] classArray = {BaseService.class};
        BaseService baseService = (BaseService) Proxy.newProxyInstance(classFile.getClassLoader(), classArray, prayingMantis);

        // 4.创建代理实现类 黄雀 二级代理
        InvocationHandler cardinal = new Cardinal(baseService);
        BaseService secondBaseService = (BaseService) Proxy.newProxyInstance(classFile.getClassLoader(),classArray,cardinal);

        // 假设要实现三级，四级代理，则在黄雀类上再加一层代理即可实现。
        // 省略其他的更多级代理对象
        return secondBaseService;
    }


    public static void main(String[] args) {
        BaseService baseService = MultiBeanFactory.newInstanc(Cicada.class);
        baseService.mainService();
    }
}
