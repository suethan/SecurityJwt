package com.ethan.auth.services;

import com.ethan.auth.entity.SysUser;
import com.ethan.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/9 16:29
 */
@Service
public class UserServices {

    @Autowired
    UserMapper userMapper;

    public SysUser getByName(String userName){

        return userMapper.selectByName(userName);
    }


}
