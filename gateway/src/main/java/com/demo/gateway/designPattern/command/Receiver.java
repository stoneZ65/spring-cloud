package com.demo.gateway.designPattern.command;

/**
 * @description: 命令模式 - 接收者
 * @author: zhanglei
 * @date: 2021/7/31 16:24
 **/
public class Receiver {

    public void charge() {
        System.out.println("收取消息");
    }

    public void issue() {
        System.out.println("发布消息");
    }
}
