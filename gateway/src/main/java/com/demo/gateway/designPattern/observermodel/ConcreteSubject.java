package com.demo.gateway.designPattern.observermodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhanglei
 * @date:
 **/
public class ConcreteSubject implements Subject {

    /**
     * 订阅者容器
     */
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public static void main(String[] args) {
        Integer a = 12;
        Integer b = 2;


    }

    public static Integer div(Integer a, Integer b) throws Exception {
        int ret = 0;
        if (b == 0) {
            throw new Exception("error");
        }
        if (a == 0) {
            return ret;
        }


        return ret;
    }
}
