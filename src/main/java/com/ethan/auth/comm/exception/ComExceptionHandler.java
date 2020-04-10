package com.ethan.auth.comm.exception;

import com.ethan.auth.comm.constant.HttpStatus;
import com.ethan.auth.comm.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * @Author: ethan.liu
 * @Date: 2020/4/3 10:10
 */
@ControllerAdvice
public class ComExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo Handler(Exception e){
        if (e instanceof ComException){
            ComException tmp = (ComException) e;
            return ResultVo.error(tmp.getCode(),tmp.getMsg());
        }else{
            return ResultVo.error(HttpStatus.AUTH_ERROR,"全局异常，未知错误:"+e.getMessage());
        }
    }
}
