package com.demo.gateway.designPattern.interpreter;

/**
 * @description: 解释器模式 - 乘法表达式
 * @author: zhanglei
 * @date: 2021/7/16 15:56
 **/
public class MultiplyExpression implements Expression {

    Expression left;

    Expression right;

    /**
     * 法表达式
     *
     * @param left
     * @param right
     */
    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Long interpret() {
        return left.interpret() * right.interpret();
    }
}
