package com.demo.gateway.designPattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
public class CglibPrayingMantis implements MethodInterceptor {

    private Cicada cicada;

    public Cicada getInstance(Cicada cicada) {
        this.cicada = cicada;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.cicada.getClass());
        enhancer.setCallback(this);
        return (Cicada) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object object = methodProxy.invokeSuper(o, objects);
        secondaryMain();
        return object;
    }

    private void secondaryMain() {
        System.out.println("螳螂捕蝉 - 次要业务");
    }

    public static void main(String[] args) {
        CglibPrayingMantis prayingMantis = new CglibPrayingMantis();
        Cicada cicada = prayingMantis.getInstance(new Cicada());
        cicada.mainService();
    }
}
