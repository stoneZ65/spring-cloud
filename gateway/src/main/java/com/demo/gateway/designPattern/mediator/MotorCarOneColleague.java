package com.demo.gateway.designPattern.mediator;

import org.springframework.stereotype.Component;

/**
 * @description: 具体参与者
 * @author: zhanglei
 * @date: 2021/8/4 15:03
 **/

public class MotorCarOneColleague implements Colleague {

    @Override
    public void message() {
        System.out.println("1号收到消息！");
    }
}

 class MotorCarTwoColleague implements Colleague {

    @Override
    public void message() {
        System.out.println("2号收到消息！");
    }
}


class MotorCarThreeColleague implements Colleague {

    @Override
    public void message() {
        System.out.println("3号收到消息！");
    }
}
