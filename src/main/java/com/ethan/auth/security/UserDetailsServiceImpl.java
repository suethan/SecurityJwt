package com.ethan.auth.security;

import com.ethan.auth.entity.SysUser;
import com.ethan.auth.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/9 16:23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserServices userServices;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser =userServices.getByName(username);
        if (sysUser!=null){
            return new LoginUser(sysUser,null);
        }
        return null;
    }
}
