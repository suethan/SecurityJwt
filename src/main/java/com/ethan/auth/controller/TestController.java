package com.ethan.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.ethan.auth.comm.vo.NamePwdVo;
import com.ethan.auth.comm.vo.ResultVo;
import com.ethan.auth.security.AuthServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/8 14:11
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    AuthServices authServices;

    @GetMapping("/test")
    public ResultVo get(HttpServletRequest request){
        return ResultVo.error("123");
    }

    @PostMapping("login")
    public ResultVo login(@RequestBody NamePwdVo namePwdVo){
        log.info("---------TestController");
        return authServices.login(namePwdVo);
    }

}
