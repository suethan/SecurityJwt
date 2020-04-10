package com.ethan.auth.comm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/3 10:16
 */
@Data
@AllArgsConstructor
public class ComException extends RuntimeException {
    private  int code;
    private  String msg;
}
