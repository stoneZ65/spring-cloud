package com.demo.gateway.designPattern.chainResponsibility.interview;

/**
 * @description:
 * @author: zhanglei
 * @date: 2021/8/4 16:24
 **/
public class FirstInterview extends Hander {
    @Override
    public void handleRequest(Integer times) {
        // 条件判断是否是属于当前Handler的处理范围之内，不是则向下传递Handler处理器
        if (times == 1) {
            System.out.println("第一次：" + times);

        }
        hander.handleRequest(times);
    }
}

class SecondInterview extends Hander {
    @Override
    public void handleRequest(Integer times) {
        // 条件判断是否是属于当前Handler的处理范围之内，不是则向下传递Handler处理器
        if (times == 2) {
            System.out.println("第二次：" + times);
        }
        hander.handleRequest(times);
    }
}

class ThreeInterview extends Hander {

    @Override
    public void handleRequest(Integer times) {
        if (times == 3) {
            System.out.println("over");
        }
    }

    public static void main(String[] args) {
        Hander first = new FirstInterview();
        Hander second = new SecondInterview();
        Hander three = new ThreeInterview();

        first.setHander(second);
        second.setHander(three);

        first.handleRequest(1);
        System.out.println();
        first.handleRequest(2);
        System.out.println();
        first.handleRequest(3);

    }
}
