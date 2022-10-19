package com.demo.gateway.designPattern.observermodel;

/**
 * @description: 测试类
 * @author: zhanglei
 * @date:
 **/
public class Test {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new FriendOneObserver());

        //这里假设是添加好友
        FriendTwoObserver twoObserver = new FriendTwoObserver();
        subject.attach(twoObserver);

        //发送朋友圈动态
        subject.notifyObservers("第一个朋友圈消息");

        // 这里删除twoObserver
        subject.detach(twoObserver);

        subject.notifyObservers("第二个朋友圈消息");
    }
}
