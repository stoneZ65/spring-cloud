package com.demo.gateway.queue;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
public interface ITask {

    /**
     * 任务
     * @throws Exception
     */
    void wrok() throws Exception;

    /**
     * 获取方法的名称
     * @return
     */
    String getTaskName();
}
