package com.demo.gateway.designPattern.interpreter;

/**
 * @description: 解释器模式 - demo
 * @author: zhanglei
 * @date: 2021/7/16 16:14
 **/
public class ExpressionPatternDemo {

    public static void main(String[] args) {
        Context context = new Context();
        Long result = context.ExpressionInterpreter("6 6 + 3 *");
        System.out.println(result);

        Context contextTow = new Context();
        Long resultTwo = contextTow.ExpressionInterpreter("2 3 * 6 +");
        System.out.println(resultTwo);
    }
}
