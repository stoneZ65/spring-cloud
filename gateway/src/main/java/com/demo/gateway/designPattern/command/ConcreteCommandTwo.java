package com.demo.gateway.designPattern.command;

/**
 * @description: 具体命令类two，发布消息
 * @author: zhanglei
 * @date: 2021/7/31 16:28
 **/
public class ConcreteCommandTwo implements Command {

    private Receiver receiver;

    public ConcreteCommandTwo(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        // 发布消息
        receiver.issue();
    }
}
