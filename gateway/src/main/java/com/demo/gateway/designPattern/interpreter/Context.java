package com.demo.gateway.designPattern.interpreter;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: 解释器模式  - 定义环境具体的运算规则，需要注意的是后续如果有更多的运算符号只需接着在后面添加就可以了。
 * @author: zhanglei
 * @date: 2021/7/16 16:00
 **/
public class Context {

    private Deque<Expression> numbers = new LinkedList<>();

    public Long ExpressionInterpreter(String Expression) {
        Long result = null;
        for (String ex : Expression.split(" ")) {
            if (isOperator(ex)) {
                Expression exp = null;
                if (ex.equals("+")) {
                    exp = new AddExpression(numbers.pollFirst(), numbers.pollFirst());
                }
                if (ex.equals("*")) {
                    exp = new MultiplyExpression(numbers.pollFirst(), numbers.pollFirst());
                }
                //当还有其他的运算符时，接着在这里添加就行了

                if (null != exp) {
                    result = exp.interpret();
                    numbers.addFirst(new NumberExpression(result));
                }
            }
            if (isNumber(ex)) {
                numbers.addLast(new NumberExpression(Long.parseLong(ex)));
            }
        }
        return result;
    }

    /**
     * 判断是否是运算符号，这里举例只用了➕和✖️，可以扩展更多的其它运算符
     *
     * @param ex
     * @return
     */
    private boolean isOperator(String ex) {
        return ex.equals("+") || ex.equals("*");
    }

    /**
     * 判断是否是数字
     *
     * @param ex
     * @return
     */
    private boolean isNumber(String ex) {
        return ex.chars().allMatch(Character::isDigit);
    }
}
