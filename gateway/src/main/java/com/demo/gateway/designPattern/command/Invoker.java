package com.demo.gateway.designPattern.command;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 命令模式 - 调用者
 * @author: zhanglei
 * @date: 2021/7/31 16:30
 **/
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    // 本次需要执行的命令
    public void action() {
        command.execute();
    }

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command commandOne = new ConcreteCommandOne(receiver);
        Command commandTwo = new ConcreteCommandTwo(receiver);

        Queue<Command> queue = new LinkedList<>();
        queue.add(commandOne);
        queue.add(commandTwo);

        queue.forEach(e -> {
            Invoker invoker = new Invoker(e);
            invoker.action();
        });
    }
}
