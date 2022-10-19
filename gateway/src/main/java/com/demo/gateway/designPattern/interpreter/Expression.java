package com.demo.gateway.designPattern.interpreter;

/**
 * @description: 解释器模式 - 定义抽象表达式
 * @author: zhanglei
 * @date: 2021/7/16 15:55
 **/
public interface Expression {
    /**
     * 定义抽象表达式
     *
     * @return
     */
    Long interpret();
}
