package com.demo.gateway.designPattern.interpreter;

/**
 * @description: 解释器模式 - 数字表达式 上面很简单就是定义一个抽象表达式接口，声明解释器的具体方法interpret，同时定义不同的语法NonTerminalExpression
 * @author: zhanglei
 * @date: 2021/7/16 15:58
 **/
public class NumberExpression implements Expression {
    private Long number;

    public NumberExpression(Long number) {
        this.number = number;
    }

    @Override
    public Long interpret() {
        return number;
    }
}
