package com.demo.gateway.designPattern.command;

/**
 * @description: 具体命令类one，收取命令
 * @author: zhanglei
 * @date: 2021/7/31 16:27
 **/
public class ConcreteCommandOne implements Command {

    private Receiver receiver;

    public ConcreteCommandOne(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.charge();
    }
}
