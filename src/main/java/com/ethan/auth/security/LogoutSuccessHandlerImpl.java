package com.ethan.auth.security;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.ethan.auth.comm.constant.HttpStatus;
import com.ethan.auth.comm.vo.ResultVo;
import com.ethan.auth.untils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义退出处理类 返回成功
 * 
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
//    @Autowired
//    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        //LoginUser loginUser = tokenService.getLoginUser(request);
        {
           // String userName = loginUser.getUsername();
            // 删除用户缓存记录
          //  tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
        }

        ServletUtils.renderString(response,JSON.toJSONString(ResultVo.success("exit success")));
    }
}