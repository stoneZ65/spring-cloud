package com.demo.baseserver.utils.page;

import com.demo.baseserver.constant.ErrorEnum;
import lombok.Data;

/**
 * @description:
 * @author: zhanglei
 * @date: 2022/8/4 11:19
 **/
@Data

public class DataResult<T> {

    private T data;

    private int code;

    private String msg;

    public DataResult() {
        this.code = ErrorEnum.SUCCESS_CODE.getCode();
        this.msg = ErrorEnum.SUCCESS_CODE.getMsg();
    }


    public static DataResult ok() {
        DataResult result = new DataResult();
        result.code = ErrorEnum.SUCCESS_CODE.getCode();
        result.msg = ErrorEnum.SUCCESS_CODE.getMsg();
        return result;
    }

    public static <T> DataResult ok(T data) {
        DataResult result = new DataResult();
        result.code = ErrorEnum.SUCCESS_CODE.getCode();
        result.msg = ErrorEnum.SUCCESS_CODE.getMsg();
        result.data = data;
        return result;
    }

    public static DataResult error() {
        DataResult result = new DataResult();
        result.code = ErrorEnum.SYS_400.getCode();
        result.msg = ErrorEnum.SYS_400.getMsg();
        return result;
    }

    public static DataResult error(String msg) {
        DataResult result = new DataResult();
        result.code = ErrorEnum.SYS_400.getCode();
        result.msg = msg;
        return result;
    }

    public static DataResult error(int code, String msg) {
        DataResult result = new DataResult();
        result.code = ErrorEnum.SYS_400.getCode();
        result.msg = ErrorEnum.SYS_400.getMsg();
        return result;
    }


}
