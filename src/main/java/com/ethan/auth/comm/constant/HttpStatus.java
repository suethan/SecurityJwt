package com.ethan.auth.comm.constant;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/2 16:25
 */
public class HttpStatus {

    public static final int HTTP_SUCCESS = 200;
    public static final int SUCCESS = 1;
    public static final String SUCCESS_MSG = "ok";

    public static final int ERROR = -1;
    public static final String ERROR_MSG = "error";

    public static final int AUTH_ERROR = 401;
    public static final String AUTH_ERROR_MSG = "No permission";
    public static final int AUTH_ERROR_TOKEN_NULL = 402;
    public static final String AUTH_ERROR_TOKEN_NULL_MSG = "token null";

    public static final int LOGIN_ERROR_TOKEN_NULL = 403;
    public static final String LOGIN_ERROR_TOKEN_NULL_MSG = "login error";

    public static final int INNER_ERROR = 500;
    public static final String INNER_ERROR_MSG = "inner error";
}
