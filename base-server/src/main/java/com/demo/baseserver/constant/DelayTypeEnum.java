package com.demo.baseserver.constant;

import lombok.Getter;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/30 17:28
 **/
@Getter
public enum DelayTypeEnum {

    /***
     * 十秒
     */
    TEN_S(1, 60 * 10),

    /**
     * 百秒
     */
    HUNDRED_S(2, 60 * 100);

    private Integer type;

    private Integer delayTime;

    DelayTypeEnum(Integer type, Integer delayTime) {
        this.type = type;
        this.delayTime = delayTime;
    }
}
