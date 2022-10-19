package com.demo.gateway.queue;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
@Component
public class TaskThread extends Thread {

    @Autowired
    RequestQueue queue;

    private Boolean isRuning = true;

    @SneakyThrows
    @Override
    public void run() {
        while (isRuning) {
            if (queue.getTaskQueue() == null || queue.getTaskQueue().size() <= 0) {
                continue;
            }
            ITask task = queue.getTaskQueue().take();
            task.wrok();
            System.out.println("任务执行完成");
        }
    }
}
