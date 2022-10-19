package com.demo.gateway.designPattern.interpreter;

/**
 * @description: 解释器模式 - 加法表达式
 * @author: zhanglei
 * @date: 2021/7/16 16:08
 **/
public class AddExpression implements Expression {

    Expression left;

    Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Long interpret() {
        return left.interpret() + right.interpret();
    }
}
