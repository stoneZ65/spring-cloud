package com.demo.gateway.designPattern.chainResponsibility;

/**
 * @description: 责任链 - 抽象处理器
 * @author: zhanglei
 * @date: 2021/8/4 15:28
 **/
public abstract class AbstractDataHandler<T> {
    protected abstract T doRequest(String query);
}
