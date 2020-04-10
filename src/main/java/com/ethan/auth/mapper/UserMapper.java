package com.ethan.auth.mapper;

import com.ethan.auth.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/9 16:39
 */
public interface UserMapper {

    SysUser selectByName(@Param("userName")String userName);
}
