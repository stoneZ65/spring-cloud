package com.demo.gateway.designPattern.observermodel;

/**
 * @description: 观察者类，实现观察者接口的update方法，处理本身的业务逻辑
 * @author: zhanglei
 * @date:
 **/
public class FriendOneObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("FriendOne 知道了你发动态了:" + message);
    }
}
