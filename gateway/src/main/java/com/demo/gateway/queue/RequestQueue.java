package com.demo.gateway.queue;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
@Data
@Component
public class RequestQueue {
    private BlockingQueue<ITask> taskQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<ITask> getTaskQueue() {
        return taskQueue;
    }
}
