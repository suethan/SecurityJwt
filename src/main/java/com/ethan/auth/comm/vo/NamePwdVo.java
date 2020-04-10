package com.ethan.auth.comm.vo;

import com.ethan.auth.comm.constant.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: ethan.liu
 * @Date: 2020/4/2 16:10
 */
@Data
public class NamePwdVo {
    private String name;
    private String pwd;

}
