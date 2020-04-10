package com.ethan.auth.comm.vo;

import com.ethan.auth.comm.constant.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/2 16:10
 */
@Data
@AllArgsConstructor
public class ResultVo {

    /*状态码*/
    private int code;
    /*返回实体*/
    private Object data;
    /*说明*/
    private String msg;

    public static ResultVo success(int code, Object data, String msg) {
        return new ResultVo(code, data, msg);
    }

    public static ResultVo success(Object data) {
        return success(HttpStatus.SUCCESS, data, HttpStatus.SUCCESS_MSG);
    }

    public static ResultVo success(Object data, String msg) {
        return success(HttpStatus.SUCCESS, data, msg);
    }

    public static ResultVo success(int code, Object data) {
        return success(HttpStatus.SUCCESS, data, HttpStatus.SUCCESS_MSG);
    }

    public static ResultVo error(int code, Object data, String msg) {
        return new ResultVo(code, data, msg);
    }

    public static ResultVo error(Object data) {
        return success(HttpStatus.ERROR, data, HttpStatus.ERROR_MSG);
    }

    public static ResultVo error(Object data, String msg) {
        return success(HttpStatus.ERROR, data, msg);
    }

    public static ResultVo error(int code, Object data) {
        return success(code, data, HttpStatus.ERROR_MSG);
    }
    public static ResultVo authError() {
        return new ResultVo(HttpStatus.AUTH_ERROR, null, HttpStatus.AUTH_ERROR_MSG);
    }
}
