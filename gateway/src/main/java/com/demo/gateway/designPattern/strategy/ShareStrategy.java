package com.demo.gateway.designPattern.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 策略模式
 * @author: zhanglei
 * @date: 2021-06-11
 **/
public interface ShareStrategy {
    /**
     * 定义策略执行方法
     *
     * @param param
     */
    void algorithm(String param);
}

class OrderItemShare implements ShareStrategy {

    @Override
    public void algorithm(String param) {
        System.out.println("当前分享图片是" + param);
    }
}

class SingleItemShare implements ShareStrategy {

    @Override
    public void algorithm(String param) {
        System.out.println("当前分单享商品是" + param);
    }
}

class MultiItemShare implements ShareStrategy {
    @Override
    public void algorithm(String param) {
        System.out.println("当前分多享商品是" + param);
    }
}

class ShareFactory {
    enum ShareType {

        SINGLE("single", "单商品"),
        MULTI("multi", "多商品"),
        ORDER("order", "下单");

        /**
         * 场景对应的编码
         */
        private String code;

        /**
         * 场景描述
         */
        private String desc;

        ShareType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }
    }

    private static final Map<String, ShareStrategy> shareStrategies = new HashMap<>();

    static {
        shareStrategies.put(ShareType.ORDER.getCode(), new OrderItemShare());
        shareStrategies.put(ShareType.SINGLE.getCode(), new SingleItemShare());
        shareStrategies.put(ShareType.MULTI.getCode(), new MultiItemShare());
    }

    public static ShareStrategy getShareStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return shareStrategies.get(type);
    }

    public static void main(String[] args) {
        String shareType = ShareType.ORDER.getCode();
        ShareStrategy shareStrategy = ShareFactory.getShareStrategy(shareType);
        shareStrategy.algorithm(shareType);

    }
}