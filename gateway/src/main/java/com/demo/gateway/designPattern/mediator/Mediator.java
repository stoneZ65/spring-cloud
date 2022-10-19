package com.demo.gateway.designPattern.mediator;

/**
 * @description: 抽象中介者
 * @author: zhanglei
 * @date: 2021/8/4 15:02
 **/
public interface Mediator {

    // 定义处理逻辑
    void doEvent(Colleague colleague);
}
