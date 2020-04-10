package com.ethan.auth.security;


import com.alibaba.fastjson.JSON;
import com.ethan.auth.comm.vo.ResultVo;
import com.ethan.auth.untils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author: ethan.liu
 * @date: 2020/4/3
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        ServletUtils.renderString(response, JSON.toJSONString(ResultVo.authError()));
    }
}
