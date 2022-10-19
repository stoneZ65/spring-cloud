package com.demo.gateway.designPattern.proxy;

/**
 * @description: 创建共同接口，以及真实对象蝉
 * @author: zhanglei
 * @date: 2021-07-01 16:03
 **/
public class Cicada implements BaseService {

    @Override
    public void mainService() {
        System.out.println("主要业务，以蝉为例，当蝉出现业务调用时，螳螂监听到");
    }
}

