package com.demo.baseserver.constant;

import lombok.Getter;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/4 11:22
 **/
@Getter
public enum ErrorEnum {


    SUCCESS_CODE(200, "ok"),

    SYS_400(400, "操作异常");

    private int code;

    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
