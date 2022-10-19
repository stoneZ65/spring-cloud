package com.demo.gateway.designPattern.objectPool;

/**
 * @description:
 * @author: zhanglei
 * @date: 2021/7/14 10:36
 **/
public class ClientTest {

    public static void main(String[] args) {
        ClassmateBPool classmateBPool = new ClassmateBPool();
        //初始化连接池
        classmateBPool.createPool();

        //借钱
        Money money = classmateBPool.getMoney();
        //还钱
        classmateBPool.returnMoney(money);

     /*   // 模拟10的请求
        for (int i = 0; i < 10; i++) {
            Money money1 = classmateBPool.getMoney();
        }*/

        //模拟10的请求，同时在第3次第4次的是时候还钱了
        for (int i = 0; i < 10; i++) {
            Money money1 = classmateBPool.getMoney();
            if (i == 3 || i == 4) {
                classmateBPool.returnMoney(money1);
            }
        }
    }
}
