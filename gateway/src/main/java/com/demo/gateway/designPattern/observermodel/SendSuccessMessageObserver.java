package com.demo.gateway.designPattern.observermodel;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
public class SendSuccessMessageObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("注册成功");
    }

    public static void main(String[] args) {
        ConcreteSubject subject = budildSubject();
        subject.notifyObservers("");

    }

    private static ConcreteSubject budildSubject() {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new SendSuccessMessageObserver());
        subject.attach(new SendNewPersonCouponObserver());
        return subject;
    }
}
