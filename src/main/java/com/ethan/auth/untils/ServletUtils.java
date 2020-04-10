package com.ethan.auth.untils;

import com.ethan.auth.comm.constant.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/2 16:41
 */
public class ServletUtils {

    public static String renderString(HttpServletResponse response, String string)
    {
        try
        {
            response.setStatus(HttpStatus.HTTP_SUCCESS);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }


    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

}
