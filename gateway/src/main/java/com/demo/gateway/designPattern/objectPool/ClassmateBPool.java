package com.demo.gateway.designPattern.objectPool;


import com.alibaba.fastjson.JSON;

import java.util.Iterator;
import java.util.Vector;

/**
 * @description: 对象池模式 -创建资源池类，里面包含了 初始化资源池方法createPool，以及获取空闲金额getMoney方法，以及returnMoney归还金额方法。
 * @author: zhanglei
 * @date: 2021/7/14 10:06
 **/
public class ClassmateBPool {
    //对象池大小 可以理解为理想情况下自己最多可以借2W
    public static int numObject = 2;

    // 对象池最大大小 可以理解为自己全部家当最多可以借出去钱
    public static int maxObjects = 5;

    // 存放对象池中对象的向量(Money)
    public Vector<Money> objectPool = null;

    public ClassmateBPool() {

    }

    /**
     * 初始化对象池
     */
    public synchronized void createPool() {
        // 确保对象池没有创建。如果创建了，保存对象 objectsPool 不会为空
        if (objectPool != null) {
            System.out.println("对象池已经创建");
            return;
        }

        // 创建对象池，添加资源
        objectPool = new Vector<Money>();
        for (int i = 0; i < numObject; i++) {
            objectPool.add(create());
        }
        System.out.println("对象池开始创建");
    }


    /**
     * 获取空闲金额
     *
     * @return
     */
    public synchronized Money getMoney() {
        int times = 1;
        if (objectPool == null) {
            throw new RuntimeException("未创建对象池");
        }

        //  获得一个可用的对象
        Money money;
        // 获得一个可用的对象
        money = getFreeMoney();

        //money==null ,证明当前没有可以借出去的钱了,则开始从另外的银行卡转钱过来
        while (money == null) {

            // 开始扩充对象池
            createNewMoney(2);

            // 扩充完了之后再去获取空闲的金额
            money = getFreeMoney();
            if (null != money) {
                break;
            }
            // 重试次数
            times++;
            System.out.println("重试次数：" + times);
            if (times > 3) {
                throw new RuntimeException("当前没有空闲的金额");
            }
        }
        System.out.println("借钱:" + JSON.toJSONString(money));
        // 返回获得的可用的对象
        return money;
    }


    /**
     * 归还金额方法
     *
     * @param money
     * @return
     */
    public void returnMoney(Money money) {
        // 确保对象池存在，如果对象没有创建（不存在），直接返回
        if (objectPool == null) {
            return;

        }
        Iterator<Money> iterator = objectPool.iterator();
        while (iterator.hasNext()) {
            Money returnMoney = iterator.next();
            if (money == returnMoney) {
                money.setStatus(0);
                System.out.println("还钱成功");
                break;
            }
        }
    }

    /**
     * 扩充资源池
     */
    public void createNewMoney(int increment) {
        for (int i = 0; i < increment; i++) {
            if (objectPool.size() > maxObjects) {
                return;
            }
            objectPool.add(create());
        }
    }

    /**
     * 查询空闲的资源
     *
     * @return
     */
    private Money getFreeMoney() {
        Iterator<Money> iterator = objectPool.iterator();
        while (iterator.hasNext()) {
            Money money = iterator.next();
            if (money.getStatus() == 0) {
                money.setStatus(1);
                return money;
            }
        }
        return null;
    }

    public Money create() {
        Money money = new Money();
        money.setMoney(1);
        money.setStatus(0);
        return money;
    }
}
