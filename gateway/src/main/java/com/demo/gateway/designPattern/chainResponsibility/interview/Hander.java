package com.demo.gateway.designPattern.chainResponsibility.interview;

/**
 * @description: 抽象Handler处理器
 * @author: zhanglei
 * @date: 2021/8/4 16:23
 **/
public abstract class Hander {
    protected  Hander hander;

    public void setHander(Hander hander) {
        this.hander = hander;
    }

    public abstract void handleRequest(Integer times);
}
