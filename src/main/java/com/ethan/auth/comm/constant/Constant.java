package com.ethan.auth.comm.constant;

import cn.hutool.extra.ssh.Sftp;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/2 16:25
 */
public class Constant {
    public static final String TOKEN = "token";
    public static final String SECRET = "1234@abcd";
    public static final int EXPIRETIME = 30;

    public static final String USERID_PRE = "uuid_pre";

    public static final String JWT_USER_KEY = "jwt_token";

    public static final long TIME_SECOND = 1000;

    public static final long TIME_MINUTE = 60 * TIME_SECOND;
}
