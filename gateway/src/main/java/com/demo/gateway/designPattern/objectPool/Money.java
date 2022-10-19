package com.demo.gateway.designPattern.objectPool;

import lombok.Data;

/**
 * @description: 对象池模式 - 资源类（money），里面有一个状态标志当前对象是否是被占用，这个是重点
 * @author: zhanglei
 * @date: 2021-07-14 10:01
 **/
@Data
public class Money {
    /**
     * 状态标识 (0 未被占用，1 占用)
     */
    private Integer status;

    /**
     * 资源
     */
    private Integer money;

}
