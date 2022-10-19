package com.demo.gateway.designPattern.observermodel;


/**
 * @description: 主题
 * @author: zhanglei
 * @date:
 **/
public interface Subject {

    /**
     * 添加订阅关系
     * @param observer
     */
    void attach(Observer observer);

    /**
     * 移除订阅关系
     * @param observer
     */
    void detach(Observer observer);

    /**
     * 通知订阅者
     * @param message
     */
    void notifyObservers(String message);
}
