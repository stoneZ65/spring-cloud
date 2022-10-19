package com.demo.gateway.designPattern.mediator;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description: 具体的终结者
 * @author: zhanglei
 * @date: 2021/8/4 15:04
 **/
public class DispatchCenter implements Mediator {

    @Autowired
    private List<Colleague> colleagues;

    @Override
    public void doEvent(Colleague colleague) {
        for (Colleague colleague1 : colleagues) {
            if (colleague1 == colleague) {
                continue;
            }
            colleague1.message();
        }
    }

    public static void main(String[] args) {
        //获取中介者，调度中心
        DispatchCenter dispatchCenter = new DispatchCenter();

        //一号 发送消息出去
        MotorCarOneColleague motorCarOneColleague = new MotorCarOneColleague();
        dispatchCenter.doEvent(motorCarOneColleague);
        //2号 发送消息出去
        MotorCarTwoColleague motorCarTwoColleague = new MotorCarTwoColleague();
        dispatchCenter.doEvent(motorCarTwoColleague);
    }
}
