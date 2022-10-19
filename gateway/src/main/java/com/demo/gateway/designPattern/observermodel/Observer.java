package com.demo.gateway.designPattern.observermodel;

/**
 * @description: 观察者接口，方便我们管理
 * @author: zhanglei
 * @date:
 **/
public interface Observer {

    /**
     * 处理业务逻辑
     * @param message
     */
    void update(String message);
}
