package com.demo.gateway.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description: 创建螳螂类，监听着蝉的类的动作
 * @author: zhanglei
 * @date: 2021-07-01 16:07
 **/
public class PrayingMantis implements InvocationHandler {

    private BaseService baseService;

    /**
     * 这里采用的是构建传参数，可以用反射.
     *
     * @param baseService
     */
    public PrayingMantis(BaseService baseService) {
        this.baseService = baseService;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(baseService, args);
        secondaryMain();
        return null;
    }

    /**
     * 这里理解增强业务，即我们可以在实现InvocationHandler里面添加其他的业务，
     */

    private void secondaryMain() {
        System.out.println("螳螂捕蝉 - 次要业务");
    }
}
