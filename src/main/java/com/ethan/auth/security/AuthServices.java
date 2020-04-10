package com.ethan.auth.security;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ethan.auth.comm.constant.Constant;
import com.ethan.auth.comm.constant.HttpStatus;
import com.ethan.auth.comm.exception.ComException;
import com.ethan.auth.comm.redis.RedisCache;
import com.ethan.auth.comm.vo.NamePwdVo;
import com.ethan.auth.comm.vo.ResultVo;
import com.ethan.auth.untils.IpUtils;
import com.ethan.auth.untils.JwtUntils;
import com.ethan.auth.untils.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/2 20:49
 */
@Service
@Slf4j
public class AuthServices {
    @Autowired
    RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;
    public LoginUser getLoginUser(HttpServletRequest request){
        String token = request.getParameter(Constant.TOKEN);
        if (ObjectUtil.isEmpty(token)){
            throw new ComException(HttpStatus.AUTH_ERROR_TOKEN_NULL,
                    HttpStatus.AUTH_ERROR_TOKEN_NULL_MSG);
        }
        Claims claims = JwtUntils.verifyToken(token);
        // 解析对应的权限以及用户信息
        String uuid = (String)claims.getSubject();
        LoginUser loginUser = redisCache.getCacheObject(Constant.USERID_PRE+uuid);
        log.info("uuid------"+uuid);

        return loginUser;
    }

    public ResultVo login(@RequestBody NamePwdVo namePwdVo){

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(namePwdVo.getName(),
                    namePwdVo.getPwd()));
        } catch (Exception e){
            log.info(e.getMessage());
            throw  new ComException(HttpStatus.LOGIN_ERROR_TOKEN_NULL,HttpStatus.LOGIN_ERROR_TOKEN_NULL_MSG);

        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return ResultVo.success(createToken(loginUser));
    }

    String  createToken( LoginUser loginUser){
        refreshToken(loginUser);
        return JwtUntils.encodeToken(loginUser.getToken());
    }

    void refreshToken(LoginUser loginUser){
        loginUser.setToken(IdUtil.fastUUID());
        loginUser.setLoginTime(System.currentTimeMillis());
        setUserAgent(loginUser);
        loginUser.setExpireTime(System.currentTimeMillis()+Constant.EXPIRETIME*Constant.TIME_MINUTE);
        String userKey = Constant.USERID_PRE+loginUser.getToken();
        redisCache.setCacheObject(userKey, loginUser, Constant.EXPIRETIME, TimeUnit.MINUTES);
    }
    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息 097654qw
     */
    public void setUserAgent(LoginUser loginUser)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(IpUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }




}
