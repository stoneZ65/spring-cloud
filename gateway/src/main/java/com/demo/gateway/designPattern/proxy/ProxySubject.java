package com.demo.gateway.designPattern.proxy;

/**
 * @description: 代理模式-构建代理对象
 * @author: zhanglei
 * @date:
 **/
public class ProxySubject implements Subject {

    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void doSomething() {
        realSubject.doSomething();
    }

    public ProxySubject() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.realSubject = (RealSubject) this.getClass().getClassLoader().loadClass("com.demo.gateway.designPattern.proxy.RealSubject").newInstance();
    }


    public static void main(String[] args) {
        try {
            new ProxySubject().doSomething();
        } catch (Exception ex) {

        }

        new ProxySubject(new RealSubject()).doSomething();
    }
}
