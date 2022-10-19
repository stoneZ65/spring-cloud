package com.demo.gateway.designPattern.observermodel;

/**
 * @description: 观察者类
 * @author: zhanglei
 * @date:
 **/
public class FriendTwoObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("FriendTwo 知道了你发动态了:" + message);
    }
}
